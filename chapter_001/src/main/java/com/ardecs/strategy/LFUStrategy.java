package com.ardecs.strategy;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

/**
 * @author Marat Yanbaev (yanbaevms@gmail.com)
 * @since 08.03.2019
 */

/**
 * LFU - реже всего используемый.
 *
 * @param <K> - ключ.
 */
public class LFUStrategy<K> extends CacheStrategy<K> {
    @Override
    public K getPriorityKey() {
        return getPriorityAndKey().firstEntry().getValue();
    }

    @Override
    public void removeKey(K key) {
        TreeMap<Long, K> priorityAndKey = getPriorityAndKey();
        Long priority = getKeyAndPriority().remove(key);
        if (key.equals(priorityAndKey.get(priority))) {
            K k = getKey(priority);
            if (k != null) {
                priorityAndKey.put(priority, k);
            } else {
                priorityAndKey.remove(priority);
            }
        }
    }

    private K getKey(Long priority) {
        return getKeyAndPriority().entrySet()
                .stream()
                .filter(entry -> priority.equals(entry.getValue()))
                .findFirst()
                .map(Map.Entry::getKey)
                .orElse(null);
    }

    @Override
    public void updatePriorityOfKey(K key) {
        TreeMap<Long, K> priorityAndKey = getPriorityAndKey();
        Long oldPriority = getKeyAndPriority().get(key);
        Long updatePriority = oldPriority + 1;
        getKeyAndPriority().replace(key, updatePriority);
        K k = priorityAndKey.get(oldPriority);
        if (k.equals(key)) {
            k = getKey(oldPriority);
            if (k != null) {
                priorityAndKey.put(oldPriority, k);
            } else {
                priorityAndKey.remove(oldPriority);
                if (!(priorityAndKey.containsKey(updatePriority))) {
                    priorityAndKey.put(updatePriority, key);
                }
            }
        } else if (!(priorityAndKey.containsKey(updatePriority))) {
            priorityAndKey.put(updatePriority, key);
        }
    }

    @Override
    public void putKey(K key) {
        Long newLong = 1L;
        getKeyAndPriority().put(key, newLong);
        getPriorityAndKey().put(newLong, key);
    }
}
