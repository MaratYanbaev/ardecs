package com.ardecs.cache;

import com.ardecs.strategy.*;

import java.io.Serializable;

/**
 * @author Marat Yanbaev (yanbaevms@gmail.com)
 * @since 12.03.2019
 */
class DoubleCache<K, V extends Serializable> implements Cache<K, V> {
    private MemoryCache<K, V> memoryCache;
    private FileCache<K, V> fileCache;

    public DoubleCache(StrategyType type, int capacity, String path) {
        memoryCache = new MemoryCache<>(type, capacity);
        fileCache = new FileCache<>(type, capacity, path);
    }

    @Override
    public KeyValue<K, V> putToCache(K key, V value) {
        if ((value == null) || (key == null)) {
            throw new KeyValueIsNullException("value and key can't be null");
        }
        KeyValue<K, V> keyValue = memoryCache.putToCache(key, value);
        if (keyValue != null) {
            keyValue = fileCache.putToCache(keyValue.getKey(), keyValue.getValue());
        }
        return keyValue;
    }


    @Override
    public V getFromCache(K key) {
        V v = null;
        if (memoryCache.isPresent(key)) {
            v = memoryCache.getFromCache(key);
        } else if (fileCache.isPresent(key)) {
            v = fileCache.getFromCache(key);
        }
        return v;
    }

    @Override
    public V removeFromCache(K key) {
        V value = memoryCache.removeFromCache(key);
        if (value == null) {
            value = fileCache.removeFromCache(key);
        }
        return value;
    }

    @Override
    public boolean isPresent(K key) {
        return memoryCache.isPresent(key) || fileCache.isPresent(key);
    }

    @Override
    public boolean hasEmptyPlace() {
        return fileCache.hasEmptyPlace();
    }

    /**
     * Данный метод ничего не делает
     * @return - всегда возвращает null
     */
    @Override
    public K getPriorityKey() {
        return null;
    }

    @Override
    public void clearCache() {
        memoryCache.clearCache();
        fileCache.clearCache();
    }

    @Override
    public int getCapacity() {
        return memoryCache.getCapacity() + fileCache.getCapacity();
    }

    @Override
    public int getCacheSize() {
        return memoryCache.getCacheSize() + fileCache.getCacheSize();
    }

    @Override
    public CacheStrategy<K> getStrategy() {
        return memoryCache.getStrategy();
    }

    @Override
    public boolean setPath(String path) {
        return fileCache.setPath(path);
    }
}
