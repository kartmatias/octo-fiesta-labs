package com.matias.alticci.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class AlticciSimpleCache<K, T> {
    private final long timeToLive;
    private final HashMap<Integer, Object> AlticciCacheMap;
    protected class AlticciCacheObject {

        public long lastAccessed = System.currentTimeMillis();
        public T value;
        protected AlticciCacheObject(T value) {
            this.value = value;
        }
    }
    
    public AlticciSimpleCache(long AlticciTimeToLive, final long AlticciTimerInterval, int maxItems) {
        this.timeToLive = AlticciTimeToLive * 1000;
        AlticciCacheMap = new HashMap<Integer, Object>();
        if (timeToLive > 0 && AlticciTimerInterval > 0) {
            Thread t = new Thread(new Runnable() {
                public void run() {
                    while (true) {
                        try {
                            Thread.sleep(AlticciTimerInterval * 1000);
                        } catch (InterruptedException ex) {
                            ex.printStackTrace();
                        }
                        AlticciCleanup();
                    }
                }
            });
            t.setDaemon(true);
            t.start();
        }
    }
    public void put(K key, T value) {
        synchronized (AlticciCacheMap) {
            AlticciCacheMap.put((Integer) key, new AlticciCacheObject(value));
        }
    }
    public T get(K key) {
        synchronized (AlticciCacheMap) {
            AlticciCacheObject c;
            c = (AlticciCacheObject) AlticciCacheMap.get(key);
            if (c == null)
                return null;
            else {
                c.lastAccessed = System.currentTimeMillis();
                return c.value;
            }
        }
    }
    public void remove(K key) {
        synchronized (AlticciCacheMap) {
            AlticciCacheMap.remove(key);
        }
    }
    public int size() {
        synchronized (AlticciCacheMap) {
            return AlticciCacheMap.size();
        }
    }
    public void AlticciCleanup() {
        long now = System.currentTimeMillis();
        ArrayList<K> deleteKey = null;
        synchronized (AlticciCacheMap) {
            Set<Map.Entry<Integer, Object>> itr = AlticciCacheMap.entrySet();
            deleteKey = new ArrayList<K>((AlticciCacheMap.size() / 2) + 1);
            K key = null;
            AlticciCacheObject c = null;
            for (Map.Entry<Integer, Object> item: itr) {
                key = (K) item.getKey();
                c = (AlticciCacheObject) item .getValue();
                if (c != null && (now > (timeToLive + c.lastAccessed))) {
                    deleteKey.add(key);
                }
            }
        }
        for (K key : deleteKey) {
            synchronized (AlticciCacheMap) {
                AlticciCacheMap.remove(key);
            }
            Thread.yield();
        }
    }
}
