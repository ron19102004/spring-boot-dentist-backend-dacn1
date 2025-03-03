package com.ronial.cache;

public interface RedisDistributeService {
    void release(String key);

    /**
     *
     * @param key
     * @param ttl : time to live
     */
    void refresh(String key, long ttl);
    boolean acquire(String key, String value, long ttl);
}
