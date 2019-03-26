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

    public MemoryCache(StrategyType memoryStrategy, int capacity) {
        this.memoryStrategy = getStrategy(memoryStrategy);
        this.capacity = capacity;
        this.memoryStorage = new HashMap<>(capacity);
    }

    @Override
    public Object[] putToCache(K key, V value) throws NullPointerException {
        if ((value == null) && (key == null)) {
            throw new NullPointerException("value and key can't be null");
        }
        Object[] o = null;
        if (!(hasEmptyPlace())) {
            o = new Object[2];
            K k = extractFromStrategy();
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
        V value = null;
        if (isPresent(key)) {
            memoryStrategy.removeFromStrategy(key);
            value = memoryStorage.remove(key);
        }
        return value;
    }

    @Override
    public boolean isPresent(K key) {
        return memoryStorage.containsKey(key);
    }

    @Override
    public boolean hasEmptyPlace() {
        return capacity > memoryStorage.size();
    }

    @Override
    public K extractFromStrategy() {
        return memoryStrategy.extractKey();
    }

    @Override
    public void clearCache() {
        memoryStorage.clear();
        memoryStrategy.clear();
    }

    @Override
    public boolean changeStrategy(StrategyType type) {
        if (this.memoryStorage.size() == 0) {
            this.memoryStrategy = getStrategy(type);
            return true;
        }
        return false;
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
    public int getCapacity() {
        return this.capacity;
    }

    @Override
    public int getCacheSize() {
        return memoryStorage.size();
    }

    @Override
    public CacheStrategy<K> getStrategy() {
        return this.memoryStrategy;
    }
}
