package com.zw.shiro.config;

import com.zw.shiro.shiro.ShiroRedisSessionManager;
import com.zw.shiro.shiro.realm.LoginShiroRealm;
import com.zw.shiro.utils.ShiroUtils;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.annotation.Resource;


@Configuration
public class ShiroConfig {
    @Resource
    RedisSessionDAO redisSessionDAO;
    /**
     * redis缓存管理
     */
    @Resource
    RedisCacheManager redisCacheManager;
    
    /**
     * 核心配置
     *
     * @return
     */
    @Bean("shiroRealm")
    public LoginShiroRealm shiroRealm(SimpleCredentialsMatcher credentialsMatcher) {
        LoginShiroRealm realm = new LoginShiroRealm();
        realm.setCredentialsMatcher(credentialsMatcher);
        return realm;
    }

    /**
     * 核心配置
     * 创建DefaultWebSecurityManager
     */
    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("shiroRealm") LoginShiroRealm userRealm,
                                                                  SessionManager sessionManager) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //关联realm
        securityManager.setRealm(userRealm);
        //将自定义的会话管理器注册到安全管理器中
        securityManager.setSessionManager(sessionManager);
        // 设置缓存管理类
        securityManager.setCacheManager(redisCacheManager);
        return securityManager;
    }

    /**
     * session管理类
     *
     * @return
     */
    @Bean
    @Primary
    public ShiroRedisSessionManager sessionManager() {
        ShiroRedisSessionManager sessionManager = new ShiroRedisSessionManager();
        sessionManager.setSessionDAO(redisSessionDAO);
        sessionManager.setGlobalSessionTimeout(1800000);
        //是否开启删除无效的session对象  默认为true
        sessionManager.setDeleteInvalidSessions(true);
        //是否开启定时调度器进行检测过期session 默认为true
        sessionManager.setSessionValidationSchedulerEnabled(true);
        //设置session失效的扫描时间, 清理用户直接关闭浏览器造成的孤立会话 默认为 1个小时
        //设置该属性 就不需要设置 ExecutorServiceSessionValidationScheduler 底层也是默认自动调用ExecutorServiceSessionValidationScheduler
        sessionManager.setSessionValidationInterval(3600000);
        //取消url 后面的 JSESSIONID
        sessionManager.setSessionIdUrlRewritingEnabled(false);
        return sessionManager;
    }
    @Bean
    public ShiroFilterChainDefinition shirFilter() {
        DefaultShiroFilterChainDefinition shiroFilterFactoryBean = new DefaultShiroFilterChainDefinition();
        shiroFilterFactoryBean.addPathDefinition("/login", "anon");
        return shiroFilterFactoryBean;
    }

    /**
     * 密码加密
     *
     * @return
     */
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        // 散列算法  MD5
        // hashedCredentialsMatcher.setHashAlgorithmName(Md5Hash.ALGORITHM_NAME);
        //  散列算法，sha256算法
        hashedCredentialsMatcher.setHashAlgorithmName(Sha256Hash.ALGORITHM_NAME);
        // 设置加密次数
        hashedCredentialsMatcher.setHashIterations(ShiroUtils.HASH_ITERATIONS);
        // setStoredCredentialsHexEncoded表示是否存储散列后的密码为16进制，需要和生成密码时的一样
        //   hashedCredentialsMatcher.setStoredCredentialsHexEncoded(true);
        return hashedCredentialsMatcher;
    }
}
