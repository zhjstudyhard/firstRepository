package cn.xydata.service.impl;

import com.alicp.jetcache.Cache;
import cn.xydata.service.CacheService;
import com.alicp.jetcache.anno.CacheType;
import com.alicp.jetcache.anno.CreateCache;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @Author: haojie
 * @qq :1422471205
 * @CreateTime: 2021-07-05-16-46
 */
@Service
public class CacheServiceImpl implements CacheService {
    /**
     * description: 创建jetcache缓存
     */
    @CreateCache(name = "UserToken100.", cacheType = CacheType.REMOTE, expire = 30 * 60 * 1000, timeUnit = TimeUnit.MILLISECONDS)
    private Cache<String, Object> dataCache;

    /**
     * 添加缓存
     * @param key
     * @param value
     */
    @Override
    public void put(String key, Object value) {
        dataCache.put(key, value);
    }

    /**
     * 添加缓存
     * @param key
     * @param value
     * @param expire
     * @param timeUnit
     */
    @Override
    public void put(String key, Object value, long expire, TimeUnit timeUnit) {
        dataCache.put(key,value, expire, timeUnit);
    }

    /**
     * 获取缓存
     * @param key
     * @return
     */
    @Override
    public Object get(String key) {
        return dataCache.get(key);
    }

    /**
     * 删除缓存
     * @param key
     * @return
     */
    @Override
    public boolean remove(String key) {
        return dataCache.remove(key);
    }

}
