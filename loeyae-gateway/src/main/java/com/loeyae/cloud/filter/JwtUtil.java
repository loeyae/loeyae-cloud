package com.loeyae.cloud.filter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Date;
import java.util.Map;

/**
 * JwtUtil .
 *
 * @author ZhangYi<loeyae @ gmail.com>
 * @version 1.0
 * @date 2020-04-26
 */

public class JwtUtil {

    /**
     *
     * @param key
     * @return
     */
    public static SecretKey generalKey(String key){
        byte[] encodedKey = Base64.decodeBase64(key);
        SecretKeySpec secretKeySpec = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");

        return secretKeySpec;
    }

    /**
     * JWTEncode
     *
     * @param claims secret fields
     * @param key private key
     * @param id
     * @param issuer
     * @param subject
     * @param ttlMillis
     *
     * @return
     */
    public static String JWTEncode(Map<String, Object>claims,  String key, String id, String issuer, String subject,
                                   long ttlMillis) {

        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;


        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        SecretKey secretKey = generalKey(key);

        JwtBuilder builder = Jwts.builder()
                .setClaims(claims)
                .setId(id)
                .setIssuedAt(now)
                .setIssuer(issuer)
                .setSubject(subject)
                .signWith(signatureAlgorithm, secretKey);


        if (ttlMillis >= 0) {
            long expMillis = nowMillis + ttlMillis;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp);
        }
        return builder.compact();
    }

    /**
     * JWTDecode
     *
     * @param jwt
     * @param key
     * @return
     */
    public static Claims JWTDecode(String jwt, String key) {
        SecretKey secretKey = generalKey(key);
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(jwt).getBody();
    }
}