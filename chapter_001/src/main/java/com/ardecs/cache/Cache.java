package com.ardecs.cache;

import com.ardecs.strategy.*;

interface Cache<K, V> {

    /**
     * Хранение value = null не допускается.
     * @param key ключ
     * @param value значение/объект
     * @return null, если кэш не переполнен, иначе
     * массив типа Object в котором под индексом 0
     * хранится ключ, а под индексом 1 значение/объект
     */
    Object[] putToCache(K key, V value);

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
    K extractFromStrategy();

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

    boolean increaseCapacity(int capacity);

    CacheStrategy<K> getStrategy();

    /**
     * Метод позволяет изменить стратегию удаления
     * элемента при переполнении кэш.
     * Изменеие стратегии возможно, если кэш пустой.
     * @param type - тип стратегии.
     * @return true, если стратегия изменина, иначе false
     */
    boolean changeStrategy(StrategyType type);

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
