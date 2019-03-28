package com.ardecs.strategy;

/**
 * @author Marat Yanbaev (yanbaevms@gmail.com)
 * @since 08.03.2019
 */

import java.util.HashMap;
import java.util.TreeMap;

public abstract class CacheStrategy<K> {
    /**
     * Данная коллекция необходима
     * для обновления значения Long(Priority)
     * при просмотре объекта из хэш.
     * Priority - приоритет удаления согласно стратегии
     */
    private HashMap<K, Long> keyAndPriority;

    /**
     * По значению Priority(Long) определяющее приоритет
     * удаления, получаем соответствующий ключ,
     * который в свою очередь отображает удаляемый объект из хэш
     */
    private TreeMap<Long, K> priorityAndKey;

    CacheStrategy() {
        priorityAndKey = new TreeMap<>();
        keyAndPriority = new HashMap<>();
    }

    /**
     * Метод по приоритету
     * удаляет ключ из стратегии
     * согласно логики.
     * @return - ключ.
     */
    public abstract K getPriorityKey();

    public void removeKey(K key) {
        priorityAndKey.remove(keyAndPriority.remove(key));
    }

    /**
     *
     * @param key - ключ
     */
    public void updatePriorityOfKey(K key) {
        long newLevel = System.nanoTime();
        Long oldLevel = getKeyAndPriority().replace(key, newLevel);
        getPriorityAndKey().remove(oldLevel);
        getPriorityAndKey().put(newLevel, key);
    }

    public void putKey(K key) {
        Long newLevel = System.nanoTime();
        getKeyAndPriority().put(key, newLevel);
        getPriorityAndKey().put(newLevel, key);
    }

    public void clear() {
        priorityAndKey.clear();
        keyAndPriority.clear();
    }

    public HashMap<K, Long> getKeyAndPriority() {
        return keyAndPriority;
    }

    public TreeMap<Long, K> getPriorityAndKey() {
        return priorityAndKey;
    }
}

