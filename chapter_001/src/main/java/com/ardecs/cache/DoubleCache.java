package com.ardecs.cache;

import com.ardecs.strategy.*;

import java.io.Serializable;

/**
 * @author Marat Yanbaev (yanbaevms@gmail.com)
 * @since 12.03.2019
 */
class DoubleCache<K, V extends Serializable> {
    private MemoryCache<K, V> memoryCache;
    private FileCache<K, V> fileCache;
    private StrategyType strategyType = StrategyType.MRU;

    public DoubleCache() {
        memoryCache = new MemoryCache<>();
    }

    /**
     * @param key ключ
     * @param value значение
     */
    public void putToCache(K key, V value) {
        Object[] i = memoryCache.putToCache(key, value);
        if (i != null && fileCache != null) {
            fileCache.putToCache((K) i[0], (V) i[1]);
        }
    }

    /**
     * @param key ключ
     * @return отображение заданного ключа,
     * иначе null.
     */
    public V getValue(K key) {
        V v = null;
        if (memoryCache.isPresent(key)) {
            v = memoryCache.getFromCache(key);
        } else if (fileCache != null && fileCache.isPresent(key)) {
            v = fileCache.getFromCache(key);
        }
        return v;
    }

    public void clearCache() {
        memoryCache.clearCache();
        if (fileCache != null) {
            fileCache.clearCache();
        }
    }

    public static class CacheBuilder<K, V extends Serializable> {

        private DoubleCache<K, V> doubleCache;

        public CacheBuilder() {
            doubleCache = new DoubleCache<>();
        }

        public CacheBuilder(DoubleCache<K, V> doubleCache) {
            this.doubleCache = doubleCache;
        }

        /**
         * Добавление второго уровня кэш.
         * @return ссылку на объект класса CacheBuilder
         */
        public CacheBuilder addFileCache(String path) {
            if (doubleCache.fileCache == null) {
                doubleCache.fileCache = new FileCache<>(getStrategy(doubleCache.strategyType), path);
            }
            return this;
        }

        /**
         * Удаление кэш второго уровня.
         * При этом также удаляются все элементы.
         * @return ссылку на объект класса CacheBuilder
         */
        public CacheBuilder removeFileCache() {
            if (doubleCache.fileCache != null) {
                doubleCache.fileCache.clearCache();
                doubleCache.fileCache = null;
            }
            return this;
        }

        /**
         * @param strategyType - тип стратегии, которая будет использоваться
         *            вместо дефолтной реализации.
         * Изменить стратегию возможно, если кэш первого и/или второго уровня
         *             не имеют элементов.
         * @return ссылку на объект класса CacheBuilder
         */
        public CacheBuilder changeStrategy(StrategyType strategyType) {
            if (doubleCache.memoryCache.getCacheSize() == 0) {
                if (doubleCache.fileCache != null && doubleCache.fileCache.getCacheSize() == 0) {
                    doubleCache.fileCache.changeStrategy(getStrategy(strategyType));
                    doubleCache.memoryCache.changeStrategy(getStrategy(strategyType));
                    doubleCache.strategyType = strategyType;
                } else {
                    doubleCache.memoryCache.changeStrategy(getStrategy(strategyType));
                    doubleCache.strategyType = strategyType;
                }
            }
            return this;
        }

        private CacheStrategy<K> getStrategy(StrategyType strategyType) {
            CacheStrategy<K> fileStrategy = null;
            switch (strategyType) {
                case LRU:
                    fileStrategy = new LRUStrategy<>();
                    break;
                case MRU:
                    fileStrategy = new MRUStrategy<>();
                    break;
                case LFU:
                    fileStrategy = new LFUStrategy<>();
                    break;
                    default:
            }
            return fileStrategy;
        }


        /**
         * Размер кэш можно только увеличить.
         * Размер по умолчанию 5 элементов
         * @param capacity размер кэш первого уровня.
         * @return ссылку на объект класса CacheBuilder
         */
        public CacheBuilder upMemoryCapacity(int capacity) {
            if (capacity > doubleCache.memoryCache.getCapacity()) {
                doubleCache.memoryCache.increaseCapacity(capacity);
            }
            return this;
        }

        /**
         * Размер кэш можно только увеличить.
         * Размер по умолчанию 5 элементов
         * @param capacity размер кэш второго уровня.
         * @return ссылку на объект класса CacheBuilder
         */
        public CacheBuilder upFileCapacity(int capacity) {
            if (capacity > doubleCache.fileCache.getCapacity()
                    && doubleCache.fileCache != null) {
                doubleCache.fileCache.increaseCapacity(capacity);
            }
            return this;
        }
    }
}
