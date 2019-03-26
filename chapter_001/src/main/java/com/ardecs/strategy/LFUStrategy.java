package com.ardecs.strategy;

import java.util.Map;

/**
 * @author Marat Yanbaev (yanbaevms@gmail.com)
 * @since 08.03.2019
 */

/**
 * LFU - реже всего используемый.
 * @param <K> - ключ.
 */
public class LFUStrategy<K> extends CacheStrategy<K> {
    @Override
    public K extractKey() {
        Map.Entry<Long, K> entry = getStorageOfKey().pollFirstEntry();
        Long l = entry.getKey();
        K k = entry.getValue();
        getStorageOfLong().remove(k);
        Map.Entry<K, Long> mapE = getStorageOfLong().entrySet()
                .stream()
                .filter(e -> l.equals(e.getValue()))
                .findFirst()
                .orElse(null);
        if (mapE != null) {
            getStorageOfKey().put(l, mapE.getKey());
        }
        return k;
    }

    @Override
    public void removeFromStrategy(K key) {

    }

    @Override
    public void updateLongOfKey(K key) {
        Long oldLong = getStorageOfLong().get(key);
        Long updateLong = oldLong + 1;
        getStorageOfLong().replace(key, updateLong);
        K k = getStorageOfKey().get(oldLong);
        if (k.equals(key)) {
            Map.Entry<K, Long> mapE = getStorageOfLong().entrySet()
                    .stream()
                    .filter(e -> oldLong.equals(e.getValue()))
                    .findFirst()
                    .orElse(null);
            if (mapE != null) {
                getStorageOfKey().put(oldLong, mapE.getKey());
            } else {
                getStorageOfKey().remove(oldLong);
                if (!(getStorageOfKey().containsKey(updateLong))) {
                    getStorageOfKey().put(updateLong, key);
                }
            }
        } else if (!(getStorageOfKey().containsKey(updateLong))) {
            getStorageOfKey().put(updateLong, key);
        }
    }

    @Override
    public void putKey(K key) {
        Long newLong = 1L;
        getStorageOfLong().put(key, newLong);
        getStorageOfKey().put(newLong, key);
    }
}
