package com.ws.sishuok.controller;

import com.ws.sishuok.service.EhCacheTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Luopc on 2016/8/30 0030.
 */

@RestController
@RequestMapping("/EhCacheTest")
public class EhCacheTestController {

    @Autowired
    private EhCacheTestService ehCacheTestService;

    @RequestMapping(value = "/cache", method = RequestMethod.GET)
    public String getTimestamp() {
        return ehCacheTestService.getTimestamp("aa");
    }
}
