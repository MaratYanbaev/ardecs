package com.ardecs.cache;

import com.ardecs.strategy.CacheStrategy;

import java.io.Serializable;

interface Cache<K, V> {
    boolean increaseCapacity(int capacity);

    /**
     *
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

    void clearCache();

    boolean hasEmptyPlace();

    /**
     * Метод запрашивает из памяти стратегии
     * ключ, который является приоритетным для удаления
     * из кэш.
     * Ключ при этом удаляется из стратегии.
     * @return - ключ.
     */
    K removeFromStrategy();

    CacheStrategy<K> getStrategy();

    /**
     * @return возвращает колличество элементов
     * сохраненых в кэш
     */
    int getCacheSize();

    /**
     * Метод позволяет изменить стратегию удаления
     * элемента при переполнении кэш.
     * Изменеие стратегии возможно, если кэш пустой.
     * @param type - тип стратегии.
     * @return true, если стратегия изменина, иначе false
     */
    boolean changeStrategy(CacheStrategy<K> type);
}
