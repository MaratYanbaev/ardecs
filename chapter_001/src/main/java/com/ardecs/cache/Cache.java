package com.ardecs.cache;

import com.ardecs.strategy.*;

interface Cache<K, V> {

    /**
     * Хранение value и key = null не допускается, иначе
     * будет выброшено исключение
     * @param key ключ
     * @param value значение/объект
     * @return null, если кэш не переполнен, иначе
     * объект типа KeyValue, который содержит ключ и значение.
     */
    KeyValue<K, V> putToCache(K key, V value);

    V getFromCache(K key);

    V removeFromCache(K key);

    boolean isPresent(K key);

    boolean hasEmptyPlace();

    /**
     * Метод запрашивает из памяти стратегии
     * ключ, который является приоритетным для удаления
     * из кэш.
     * Ключ при этом удаляется из стратегии.
     * @return - ключ.
     */
    K getPriorityKey();

    void clearCache();

    default CacheStrategy<K> getStrategy(StrategyType strategyType) {
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

    CacheStrategy<K> getStrategy();

    /**
     * @return возвращает колличество элементов
     * сохраненых в кэш
     */
    int getCacheSize();

    int getCapacity();

    /**
     * Указать новый путь для записи файлов
     * возможно только, если кэш пустой.
     * @param path - путь
     * @return true, если кэш пустой, иначе false
     */
    default boolean setPath(String path) {
        return false;
    }
}
