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

    public DoubleCache() {
        memoryCache = new MemoryCache<>();
        fileCache = new FileCache<>();
    }

    public DoubleCache(String path) {
        memoryCache = new MemoryCache<>();
        fileCache = new FileCache<>(path);
    }

    public DoubleCache(StrategyType type, int capacity, String path) {
        memoryCache = new MemoryCache<>(type, capacity);
        fileCache = new FileCache<>(type, capacity, path);
    }

    @Override
    public Object[] putToCache(K key, V value) {
        Object[] i = memoryCache.putToCache(key, value);
        if (i != null) {
            i = fileCache.putToCache((K) i[0], (V) i[1]);
        }
        return i;
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
        return null;
    }

    @Override
    public boolean isPresent(K key) {
        return memoryCache.isPresent(key) || fileCache.isPresent(key);
    }

    @Override
    public boolean hasEmptyPlace() {
        return false;
    }

    @Override
    public K extractFromStrategy() {
        return null;
    }

    @Override
    public void clearCache() {
        memoryCache.clearCache();
        fileCache.clearCache();
    }

    @Override
    public boolean changeStrategy(StrategyType type) {
        if (memoryCache.changeStrategy(type)) {
            fileCache.changeStrategy(type);
            return true;
        }
        return false;
    }

    @Override
    public boolean increaseCapacity(int capacity) {
        return memoryCache.increaseCapacity(capacity)
                && fileCache.increaseCapacity(capacity);
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
