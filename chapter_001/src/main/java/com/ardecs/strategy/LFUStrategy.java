package com.ardecs.strategy;

/**
 * @author Marat Yanbaev (yanbaevms@gmail.com)
 * @since 08.03.2019
 */
public class LFUStrategy<K> extends CacheStrategy<K> {
    @Override
    public void putObject(K key) {
        long frequency = 1;
        if (getObjectsStorage().containsKey(key)) {
            frequency = getObjectsStorage().get(key) + 1;
        }
        getObjectsStorage().put(key, frequency);
    }
}
