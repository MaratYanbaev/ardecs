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
    public KeyValue<K, V> putToCache(K key, V value) {
        if ((value == null) || (key == null)) {
            throw new KeyValueIsNullException("value and key can't be null");
        }
        KeyValue<K, V> keyValue = null;
        if (!(hasEmptyPlace())) {
            K k = getPriorityKey();
            V v = removeFromCache(k);
            keyValue = new KeyValue<>(k, v);
            memoryStorage.put(key, value);
            memoryStrategy.putKey(key);
        } else {
            memoryStorage.put(key, value);
            memoryStrategy.putKey(key);
        }
        return keyValue;
    }

    @Override
    public V getFromCache(K key) {
        V value = null;
        if (isPresent(key)) {
            value = memoryStorage.get(key);
            memoryStrategy.updatePriorityOfKey(key);
        }
        return value;
    }

    @Override
    public V removeFromCache(K key) {
        V value = null;
        if (isPresent(key)) {
            memoryStrategy.removeKey(key);
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
    public K getPriorityKey() {
        return memoryStrategy.getPriorityKey();
    }

    @Override
    public void clearCache() {
        memoryStorage.clear();
        memoryStrategy.clear();
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
