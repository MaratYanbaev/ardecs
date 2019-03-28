package com.ardecs.strategy;

/**
 * @author Marat Yanbaev (yanbaevms@gmail.com)
 * @since 08.03.2019
 */

/**
 * LRU - давно просматривавшийся.
 * @param <K> - ключ.
 */
public class LRUStrategy<K> extends CacheStrategy<K> {

    @Override
    public K getPriorityKey() {
        return getPriorityAndKey().firstEntry().getValue();
    }
}