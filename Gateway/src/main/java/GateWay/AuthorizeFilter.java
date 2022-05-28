package GateWay;

import com.June.Common.Utils.jwtUtil;
import com.June.Common.Vo.Login.jwtEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Consumer;

/**
 * June
 */
@Order(100000)
@Component
public class AuthorizeFilter implements GlobalFilter {

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();
        MultiValueMap<String, String> params = request.getQueryParams();
        if (!StringUtils.isEmpty(request.getHeaders().get("tokenuseless"))) {
            System.out.println(request);
            System.out.println("1");
            return chain.filter(exchange);
        }
        System.out.println("2");

        String token = request.getHeaders().getFirst("token");
        System.out.println(request.getHeaders());
        System.out.println(token);
        if (token.equals("null")) {
            response.setStatusCode(HttpStatus.UNAUTHORIZED);//401
            return response.setComplete();
        }
        if (jwtEnum.TRUE != jwtUtil.checkToken(token)) {
            System.out.println("417");
            response.setStatusCode(HttpStatus.EXPECTATION_FAILED);//417
            return response.setComplete();
        }
        Long permission = Long.valueOf(redisTemplate.opsForValue().get("token:" + token).toString());
        System.out.println(permission);

        if (permission == null) {
            System.out.println("410");
            response.setStatusCode(HttpStatus.GONE);//410
            return response.setComplete();
        }

        LinkedHashMap<String, Long> headerMap = new LinkedHashMap<>();
        headerMap.put("id", permission);
        chainFilterAndSetHeaders(chain, exchange, headerMap);
        System.out.println(request.getHeaders());
//        redisTemplate.delete("token:"+token);//刷新时间
//        redisTemplate.opsForValue().set("token:"+token,permission,10, TimeUnit.MINUTES);
        return chain.filter(exchange);

    }

    private Mono<Void> chainFilterAndSetHeaders(GatewayFilterChain chain, ServerWebExchange exchange, LinkedHashMap<String,Long> headerMap) {
        Consumer<HttpHeaders> httpHeaders = httpHeader -> {
            for (Map.Entry<String, Long> entry:headerMap.entrySet()) {
                httpHeader.set(entry.getKey(), String.valueOf(entry.getValue()));
            }
        };

        ServerHttpRequest newRequest = exchange.getRequest().mutate().headers(httpHeaders).build();
        ServerWebExchange build = exchange.mutate().request(newRequest).build();
        return chain.filter(build);
    }
}
