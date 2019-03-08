package com.ardecs.strategy;

/**
 * @author Marat Yanbaev (yanbaevms@gmail.com)
 * @since 08.03.2019
 */
public class LRUStrategy<K> extends CacheStrategy<K> {
    @Override
    public void putObject(K key) {
        getObjectsStorage().put(key, System.nanoTime());
    }
}