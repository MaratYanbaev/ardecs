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
    public K getPriorityKey() {
        return getPriorityAndKey().lastEntry().getValue();
    }
}