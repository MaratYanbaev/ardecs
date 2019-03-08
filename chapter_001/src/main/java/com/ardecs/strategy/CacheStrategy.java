package com.ardecs.strategy;

/**
 * @author Marat Yanbaev (yanbaevms@gmail.com)
 * @since 08.03.2019
 */

import java.util.Map;
import java.util.TreeMap;

public abstract class CacheStrategy<K> {
    private final Map<K, Long> objectsStorage;
    private final TreeMap<K, Long> sortedObjectsStorage;

    CacheStrategy() {
        this.objectsStorage = new TreeMap<>();
        this.sortedObjectsStorage = new TreeMap<>(new ComparatorImpl<>(objectsStorage));
    }

    Map<K, Long> getObjectsStorage() {
        return objectsStorage;
    }

    TreeMap<K, Long> getSortedObjectsStorage() {
        return sortedObjectsStorage;
    }

    public abstract void putObject(K key);

    public void removeObject(K key) {
        if (isObjectPresent(key)) {
            objectsStorage.remove(key);
        }
    }

    public boolean isObjectPresent(K key) {
        return objectsStorage.containsKey(key);
    }

    public K getReplacedKey() {
        sortedObjectsStorage.putAll(objectsStorage);
        return sortedObjectsStorage.firstKey();
    }

    public void clear() {
        objectsStorage.clear();
    }
}

