package com.ardecs.cache;

import com.ardecs.strategy.*;

/**
 * @author Marat Yanbaev (yanbaevms@gmail.com)
 * @since 25.03.2019
 */
public class CacheBuilder<K, V> {
    private StrategyType type = StrategyType.MRU;
    private CacheType cache = CacheType.MEMORY;
    private String path;
    private int capacity = 5;

    public CacheBuilder setStrategy(StrategyType type) {
            this.type = type;
        return this;
    }

    public CacheBuilder setCapacity(int capacity) {
            this.capacity = capacity;
        return this;
    }

    public CacheBuilder setPath(String path) {
            this.path = path;
        return this;
    }

    /**
     * При выборе FILE или DOUBLE тип V должен реализовывать
     * интерфейс Serializable
     *
     * @param cache - тип кэш - MEMORY, FILE или DOUBLE
     * @return - объект CacheBuilder
     */
    public CacheBuilder setCache(CacheType cache) {
            this.cache = cache;
        return this;
    }

    /**
     * Терминальная операция.
     * @return объект типа Cache
     */
    public Cache build() {
        Cache<K, V> c = null;
        switch (cache) {
            case FILE:
                c = new FileCache(type, capacity, path);
                break;
            case MEMORY:
                c = new MemoryCache<>(type, capacity);
                break;
            case DOUBLE:
                c = new DoubleCache(type, capacity, path);
            default:
        }
        return c;
    }
}
