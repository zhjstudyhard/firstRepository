package cn.xydata.service;

import java.util.concurrent.TimeUnit;

/**
 * @Author: haojie
 * @qq :1422471205
 * @CreateTime: 2021-07-05-16-45
 */
public interface CacheService {
    /**
     * 添加缓存
     * @param key
     * @param value
     */
    public void put(String key, Object value);

    /**
     * 添加缓存
     * @param key
     * @param value
     * @param expire
     * @param timeUnit
     */
    public void put(String key, Object value, long expire, TimeUnit timeUnit);

    /**
     * 获取缓存
     * @param key
     * @return
     */
    public Object get(String key);

    /**
     * 删除缓存
     * @param key
     * @return
     */
    public boolean remove(String key);

}
