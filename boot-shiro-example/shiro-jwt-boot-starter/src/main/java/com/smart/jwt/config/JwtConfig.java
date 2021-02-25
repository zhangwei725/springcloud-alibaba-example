package com.smart.jwt.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Data
@ConfigurationProperties(prefix = "shiro.jwt")
public class JwtConfig {
    // JWT 在 HTTP HEADER 中默认的 KEY
    private String token;
    // HMAC 密钥，用于支持 HMAC 算法
    private String hmacKey;

    // JKS 密钥路径，用于支持 RSA 算法
    private String jksFileName;

    // JKS 密钥密码，用于支持 RSA 算法
    private String jksPassword;

    // 证书密码，用于支持 RSA 算法
    private String certPassword;

    // JWT 标准信息：签发人 - iss
    private String issuer;

    // JWT 标准信息：主题 - sub
    private String subject;

    // JWT 标准信息：受众 - aud
    private String audience;

    // JWT 标准信息：生效时间 - nbf，未来多长时间内生效
    private long notBeforeIn;

    // JWT 标准信息：生效时间 - nbf，具体哪个时间生效
    private long notBeforeAt;

    // JWT 标准信息：过期时间 - exp，未来多长时间内过期
    private long expiredIn;

    // JWT 标准信息：过期时间 - exp，具体哪个时间过期
    private long expiredAt;
}  