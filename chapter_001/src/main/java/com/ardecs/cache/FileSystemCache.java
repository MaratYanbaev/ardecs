package com.ardecs.cache;

/**
 * @author Marat Yanbaev (yanbaevms@gmail.com)
 * @since 08.03.2019
 */

import java.util.Map;
import java.util.HashMap;

class FileSystemCache<K, V> implements Cache<K, V> {
    private final Map<K, V> objectsStorage;
    private int capacity;

    FileSystemCache() {
        this.objectsStorage = new HashMap<>();
    }

    FileSystemCache(int capacity) {
        this.capacity = capacity;
        this.objectsStorage = new HashMap<>(capacity);
    }

    @Override
    public V getFromCache(K key) {
        return objectsStorage.get(key);
    }

    @Override
    public void putToCache(K key, V value) {
            objectsStorage.put(key, value);
    }

    @Override
    public synchronized void removeFromCache(K key) {
        objectsStorage.remove(key);
    }

    @Override
    public int getCacheSize() {
        return objectsStorage.size();
    }

    @Override
    public boolean isObjectPresent(K key) {
        return objectsStorage.containsKey(key);
    }

    @Override
    public boolean hasEmptyPlace() {
        return getCacheSize() < this.capacity;
    }

    @Override
    public void clearCache() {
        objectsStorage.clear();
    }
}