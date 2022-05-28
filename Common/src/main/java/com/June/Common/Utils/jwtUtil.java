package com.June.Common.Utils;





//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//
//import com.nimbusds.jose.JOSEException;
//import com.nimbusds.jose.JOSEObjectType;
//import com.nimbusds.jose.JWSAlgorithm;
//import com.nimbusds.jose.JWSHeader;
//import com.nimbusds.jose.JWSObject;
//import com.nimbusds.jose.JWSVerifier;
//import com.nimbusds.jose.Payload;
//import com.nimbusds.jose.crypto.MACSigner;
//import com.nimbusds.jose.crypto.MACVerifier;
//
//import net.minidev.json.JSONObject;

import com.June.Common.Vo.Login.jwtEnum;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;

/**
 * June
 */
public class jwtUtil {


    private static final String SecretKey = "MoNoaliYah$June%Moon*ziyiY#1LoveU";



    public static String getJwtToken(Long permission){
        String JwtToken = Jwts.builder()
                //JWT头信息
                .setHeaderParam("typ", "JWT")
                .setHeaderParam("alg", "HS2256")
                //设置分类；设置过期时间 一个当前时间，一个加上设置的过期时间常量
                .setSubject(permission.toString())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() +1000*60*60*2))//有效期2hours
                .claim("permission", permission)
                //.signWith(SignatureAlgorithm.ES256, SECRET)
                .signWith(SignatureAlgorithm.HS256, SecretKey)
                .compact();
        return JwtToken;
    }

    /**
     * 判断token是否存在与有效
     * @Param jwtToken
     */
    public static jwtEnum checkToken(String jwtToken){
        if (StringUtils.isEmpty(jwtToken)){
            return jwtEnum.NULL_TOKEN;
        }
        try{
            Jwts.parser().setSigningKey(SecretKey).parseClaimsJws(jwtToken);
        }catch (SignatureVerificationException e) {
            e.printStackTrace();
            return jwtEnum.CHANGED;
        } catch (TokenExpiredException e) {
            e.printStackTrace();
            return jwtEnum.EXPIRE;
        } catch (Exception e) {
            e.printStackTrace();
            return jwtEnum.UNKNOWN;
        }
        return jwtEnum.TRUE;
    }



    /**
     * 根据token获取会员id
     * @Param request
     */
    public static Long getMemberIdByJwtToken(String token){
        if (StringUtils.isEmpty(token)){
            return null;
        }
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(SecretKey).parseClaimsJws(token);
        Claims body = claimsJws.getBody();
        return (Long) body.get("permission");
    }

}