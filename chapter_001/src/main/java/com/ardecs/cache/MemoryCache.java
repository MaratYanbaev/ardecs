package com.ardecs.cache;

import com.ardecs.strategy.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Marat Yanbaev (yanbaevms@gmail.com)
 * @since 08.03.2019
 */
class MemoryCache<K, V> implements Cache<K, V> {
    private final Map<K, V> memoryStorage;
    private CacheStrategy<K> memoryStrategy;
    private int capacity = 5;

    public MemoryCache() {
        this.memoryStrategy = new MRUStrategy<>();
        this.memoryStorage = new HashMap<>(this.capacity);
    }

    public MemoryCache(CacheStrategy<K> memoryStrategy, int capacity) {
        this.memoryStrategy = memoryStrategy;
        this.capacity = capacity;
        this.memoryStorage = new HashMap<>(capacity);
    }

    @Override
    public Object[] putToCache(K key, V value) {
        Object[] o = null;
        if (!(hasEmptyPlace())) {
            o = new Object[2];
            K k = removeFromStrategy();
            o[0] = k;
            V v = removeFromCache(k);
            o[1] = v;
            memoryStorage.put(key, value);
            memoryStrategy.putKey(key);
        } else {
            memoryStorage.put(key, value);
            memoryStrategy.putKey(key);
        }
        return o;
    }

    @Override
    public V getFromCache(K key) {
        V value = null;
        if (isPresent(key)) {
            value = memoryStorage.get(key);
            memoryStrategy.updateLongOfKey(key);
        }
        return value;
    }

    @Override
    public V removeFromCache(K key) {
        return memoryStorage.remove(key);
    }

    @Override
    public boolean isPresent(K key) {
        return memoryStorage.containsKey(key);
    }

    @Override
    public void clearCache() {
        memoryStorage.clear();
        memoryStrategy.clear();
    }

    @Override
    public boolean hasEmptyPlace() {
        return capacity > memoryStorage.size();
    }

    @Override
    public K removeFromStrategy() {
        return memoryStrategy.extractKey();
    }

    @Override
    public boolean changeStrategy(CacheStrategy<K> type) {
        if (this.memoryStorage.size() == 0) {
            this.memoryStrategy = type;
            return true;
        }
        return false;
    }

    public int getCapacity() {
        return this.capacity;
    }

    @Override
    public int getCacheSize() {
        return memoryStorage.size();
    }

    @Override
    public boolean increaseCapacity(int capacity) {
        if (capacity > this.capacity) {
            this.capacity = capacity;
            return true;
        }
        return false;
    }

    @Override
    public CacheStrategy<K> getStrategy() {
        return this.memoryStrategy;
    }
}
