package com.zw.shiro.utils;

import com.zw.shiro.dto.UserDto;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

public class ShiroUtils {

    /**
     * 循环次数
     */
    public final static int HASH_ITERATIONS = 1024;

    public static String sha256(String password, String salt) {
        return new SimpleHash(Sha256Hash.ALGORITHM_NAME, password, salt, HASH_ITERATIONS).toString();
    }

    // 获取一个测试账号 admin
    public static void main(String[] args) {
        // 3743a4c09a17e6f2829febd09ca54e627810001cf255ddcae9dabd288a949c4a
        System.out.println(sha256("123456", null));
    }

    /**
     * 获取会话
     */
    public static Session getSession() {
        return SecurityUtils.getSubject().getSession();
    }

    /**
     * Subject：主体，代表了当前“用户”
     */
    public static Subject getSubject() {
        return SecurityUtils.getSubject();
    }

    public static UserDto getUser() {
        return (UserDto) SecurityUtils.getSubject().getPrincipal();
    }

    public static Integer getUserId() {
        return getUser().getUserId();
    }

    /**
     * 在session中保存对象
     *
     * @param key
     * @param value
     */
    public static void setSessionAttribute(Object key, Object value) {
        getSession().setAttribute(key, value);
    }

    /**
     * 从session中获取数据
     *
     * @param key
     * @return
     */
    public static Object getSessionAttribute(Object key) {
        return getSession().getAttribute(key);
    }

    /**
     * 判断是否登录
     *
     * @return
     */
    public static boolean isLogin() {
        return SecurityUtils.getSubject().getPrincipal() != null;
    }

    /**
     * 登出
     */
    public static void logout() {
        SecurityUtils.getSubject().logout();
    }
}