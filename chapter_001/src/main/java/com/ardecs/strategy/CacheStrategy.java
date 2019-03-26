package com.ardecs.strategy;

/**
 * @author Marat Yanbaev (yanbaevms@gmail.com)
 * @since 08.03.2019
 */

import java.util.HashMap;
import java.util.TreeMap;

public abstract class CacheStrategy<K> {
    /**
     * Данная коллекция необходима
     * для обновления значения Long
     * при просмотре объекта из хэш.
     */
    private HashMap<K, Long> storageOfLong;

    /**
     * По значению определяющие приоритет
     * удаления, получаем соответствующий ключ,
     * который отображает удаляемый объект из хэш
     */
    private TreeMap<Long, K> storageOfKey;

    CacheStrategy() {
        storageOfKey = new TreeMap<>();
        storageOfLong = new HashMap<>();
    }

    /**
     * Метод удаляет ключ из стратегии
     * согласно логики.
     * @return - ключ.
     */
    public abstract K extractKey();

    public abstract void removeFromStrategy(K key);

    /**
     *
     * @param key - ключ
     */
    public void updateLongOfKey(K key) {
        long newLong = System.nanoTime();
        Long oldLong = getStorageOfLong().replace(key, newLong);
        getStorageOfKey().remove(oldLong);
        getStorageOfKey().put(newLong, key);
    }

    public void putKey(K key) {
        Long newLong = System.nanoTime();
        getStorageOfLong().put(key, newLong);
        getStorageOfKey().put(newLong, key);
    }

    public void clear() {
        storageOfKey.clear();
        storageOfLong.clear();
    }

    public HashMap<K, Long> getStorageOfLong() {
        return storageOfLong;
    }

    public TreeMap<Long, K> getStorageOfKey() {
        return storageOfKey;
    }
}

