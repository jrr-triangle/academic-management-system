package com.jrrtriangle.ams.config;

import org.ehcache.event.CacheEvent;
import org.ehcache.event.CacheEventListener;

public class CacheLogger implements CacheEventListener<Object, Object> {

    @Override
    public void onEvent(CacheEvent<?, ?> cacheEvent) {
        System.out.println("Key: {} | EventType: {} | Old value: {} | New value: {}"+
                cacheEvent.getKey()+ cacheEvent.getType()+ cacheEvent.getOldValue()+
                cacheEvent.getNewValue());
    }


}