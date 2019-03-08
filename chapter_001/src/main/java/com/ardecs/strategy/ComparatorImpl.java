package com.ardecs.strategy;

import java.util.Comparator;
import java.util.Map;

/**
 * @author Marat Yanbaev (yanbaevms@gmail.com)
 * @since 08.03.2019
 */
class ComparatorImpl<K> implements Comparator<K> {

    private final Map<K, Long> comparatorMap;

    public ComparatorImpl(Map<K, Long> objectsStorage) {
        this.comparatorMap = objectsStorage;
    }

    @Override
    public int compare(K key1, K key2) {
        Long key1Long = comparatorMap.get(key1);
        Long key2Long = comparatorMap.get(key2);
        if (key1Long > key2Long) {
            return 1;  //key1 must be later than key2
        }
        if (key1Long < key2Long) {
            return -1; //key1 must be earlier than key2
        }
        return 0;      //key1 and key2 are equals
    }
}
