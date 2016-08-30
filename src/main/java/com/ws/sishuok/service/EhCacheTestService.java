package com.ws.sishuok.service;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * Created by Luopc on 2016/8/30 0030.
 */
@Service
public class EhCacheTestService {

    @Cacheable(value = "cacheTest", key = "#param")
    public String getTimestamp(String param) {
        Long timestamp = System.currentTimeMillis();
        return timestamp.toString();
    }
}
