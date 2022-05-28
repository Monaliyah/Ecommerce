package com.June.Goods.service.impl;

import com.June.Common.Utils.JsonUtil;
import com.June.Common.Vo.GoodsVo.GoodsDetail;
import com.June.Common.Vo.GoodsVo.GoodsForOrder;
import com.June.Common.Vo.MQMessage.doSecKillGoodsMessage;
import com.June.Common.Vo.UserVo.Owner;
import com.June.Common.pojo.Goods;
import com.June.Common.rabbitmq.MQSender;
import com.June.Feign.Vo.RespBean;
import com.June.Feign.Vo.RespBeanEnum;
import com.June.Feign.client.UserClient;
import com.June.Goods.mapper.GoodsMapper;
import com.June.Goods.service.IGoodsService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author June
 * @since 2022-05-20
 */
@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements IGoodsService {

    @Autowired
    @Resource
    private GoodsMapper goodsMapper;
    @Autowired
    private IGoodsService goodsService;
    @Autowired
    private UserClient userClient;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private MQSender mqSender;

    @Override
    public RespBean getGoodsByUserId(Long id){
        List<Goods> goods=goodsMapper.getGoodsByUserId(id);
        if(null==goods){
            return RespBean.error(RespBeanEnum.NULL_GOODS);
        }
        return RespBean.success(goods);
    }

    @Override
    public RespBean getGoodsDetailByGoodsId(Long id) {
        Goods goods=goodsMapper.getGoodsByGoodsId(id);
        Owner owner=userClient.getOwner(goods.getUserid());
        System.out.println(owner);
        GoodsDetail goodsDetail=new GoodsDetail();
        goodsDetail.setGoods(goods);
        goodsDetail.setOwner(owner);
        return RespBean.success(goodsDetail);
    }



    @Override
    public int decGoodsStock(Long id,Integer num){
        boolean result;
        Goods goods = goodsService.getOne(new QueryWrapper<Goods>().eq("goodsid", id));
        if(goods.getGoodsstock()<=0) {
            return 2;
        }
        goods.setGoodsstock(goods.getGoodsstock() - num);
        for(int i=0;i<num;i++) {
            if (goods.getGoodsstock() == 0) {
                result = goodsService.update(new UpdateWrapper<Goods>().setSql("goodsstock=" + "goodsstock-1").setSql("status=" + "none")
                        .eq("goodsid", id));
            } else {
                result = goodsService.update(new UpdateWrapper<Goods>().setSql("goodsstock=" + "goodsstock-1")
                        .eq("goodsid", id));
            }
            if (!result) {
                return 3;
            }
        }
        return 1;
    }

    @Override
    public GoodsForOrder getGoodsVo(Long id){
        return goodsMapper.getGoodsVo(id);
    }

    @Override
    public List<Goods> getSecKillGoods(){
        return goodsMapper.getSecKillGoods();
    }

    @Override
    public RespBean doSecKillGoods(Long id, String token, Long goodsid, Integer addressid,
                                   Integer goodsnum, BigDecimal payprice, Map<Long, Boolean> emptyStockMap){
        //内存标记，减少Redis的访同
        if (emptyStockMap.get(goodsid)){
            return RespBean.error(RespBeanEnum.ERROR);
        }
        //预减库存
        Long stock =redisTemplate.opsForValue().decrement("GoodsLast:" + goodsid);
        if (stock < 0){
            emptyStockMap.put(goodsid,true);
            redisTemplate.opsForValue().increment("GoodsLast:" + goodsid);
            return RespBean.error(RespBeanEnum.ERROR);
        }
        decGoodsStock(goodsid,goodsnum);
        doSecKillGoodsMessage doSecKillGoodsMessage = new doSecKillGoodsMessage(id,token,goodsid,addressid,goodsnum,payprice);
        mqSender.senddoSecKillGoodsMessage(JsonUtil.ObjectToJsonStr(doSecKillGoodsMessage));
        return RespBean.success(0);
    }
}
