package com.ardecs.strategy;

/**
 * @author Marat Yanbaev (yanbaevms@gmail.com)
 * @since 08.03.2019
 */

/**
 * MRU - недавно просматривавшийся.
 * @param <K> - ключ.
 */
public class MRUStrategy<K> extends CacheStrategy<K> {

    @Override
    public K extractKey() {
        K key = getStorageOfKey().pollLastEntry().getValue();
        getStorageOfLong().remove(key);
        return key;
    }

    @Override
    public void removeFromStrategy(K key) {

    }
}