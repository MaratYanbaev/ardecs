package com.ardecs.strategy;

/**
 * @author Marat Yanbaev (yanbaevms@gmail.com)
 * @since 08.03.2019
 */
public class MRUStrategy<K> extends CacheStrategy<K> {
    @Override
    public void putObject(K key) {
        getObjectsStorage().put(key, System.nanoTime());
    }

    @Override
    public K getReplacedKey() {
        getSortedObjectsStorage().putAll(getObjectsStorage());
        return getSortedObjectsStorage().lastKey();
    }
}