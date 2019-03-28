package com.ardecs.cache;

import com.ardecs.strategy.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;

/**
 * @author Marat Yanbaev (yanbaevms@gmail.com)
 * @since 17.03.2019
 */
public class FileCache<K, V extends Serializable> implements Cache<K, V> {
    private CacheStrategy<K> fileStrategy;
    private File dir;
    private String path;
    private int capacity = 5;
    private List<String> savedKeys;

    public FileCache(String path) {
        this.fileStrategy = new MRUStrategy<>();
        savedKeys = new ArrayList<>(capacity);
        setPath(path);
    }

    public FileCache(StrategyType memoryStrategy, int capacity, String path) {
        this.fileStrategy = getStrategy(memoryStrategy);
        this.capacity = capacity;
        savedKeys = new ArrayList<>(capacity);
        setPath(path);
    }

    @Override
    public KeyValue<K, V> putToCache(K key, V value) {
        if ((value == null) || (key == null)) {
            throw new KeyValueIsNullException("value and key can't be null");
        }
        KeyValue<K, V> keyValue = null;
        if (!(hasEmptyPlace())) {
            K k = getPriorityKey();
            V v = removeFromCache(k);
            keyValue = new KeyValue<>(k, v);
            writeDate(key, value);
        } else {
            writeDate(key, value);
        }
        return keyValue;
    }

    @Override
    public V getFromCache(K key) {
        V value = null;
        if (isPresent(key)) {
            value = get(key);
            fileStrategy.updatePriorityOfKey(key);
        }
        return value;
    }

    @Override
    public V removeFromCache(K key) {
        V value = null;
        if (isPresent(key)) {
            value = get(key);
            try {
                Files.delete(Path.of(path, key.toString()));
            } catch (IOException e) {
                e.printStackTrace();
            }
            fileStrategy.removeKey(key);
            savedKeys = asList(dir.list());
        }
        return value;
    }

    @Override
    public boolean isPresent(K key) {
        String k = String.valueOf(key);
        return savedKeys.contains(k);
    }

    @Override
    public boolean hasEmptyPlace() {
        return savedKeys.size() < capacity;
    }

    @Override
    public K getPriorityKey() {
        return fileStrategy.getPriorityKey();
    }

    @Override
    public void clearCache() {
        if (savedKeys.size() > 0) {
            try {
                for (String key : savedKeys) {
                    Files.delete(Path.of(path, key));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        savedKeys = asList(dir.list());
        fileStrategy.clear();

    }

    @Override
    public CacheStrategy<K> getStrategy() {
        return fileStrategy;
    }

    @Override
    public int getCacheSize() {
        return savedKeys.size();
    }

    @Override
    public int getCapacity() {
        return capacity;
    }

    /**
     * Указать новый путь для записи файлов
     * возможно только, если файловый кэш пустой.
     *
     * @param path - путь
     * @return true, если кэш пустой, иначе false
     */
    @Override
    public boolean setPath(String path) {
        if (getCacheSize() == 0) {
            if (path != null) {
                this.dir = new File(path);
                this.path = path;
                return true;
            } else {
                this.dir = new File("objects");
                dir.mkdir();
                this.path = dir.getPath();
            }
        }
        return false;
    }

    /**
     * Метод записи на диск значения/value
     *
     * @param key   - ключ/имя файла
     * @param value - данные
     */
    private void writeDate(K key, V value) {
        String file = key.toString();
        try (ObjectOutputStream os = new ObjectOutputStream(
                new BufferedOutputStream(
                        new FileOutputStream(new File(path, file))))) {
            os.writeObject(value);
        } catch (IOException e) {
            e.printStackTrace();
        }
        fileStrategy.putKey(key);
        savedKeys = asList(dir.list());
    }

    /**
     * Метод полученияс диска значения/value по ключу
     *
     * @param key - ключ/имя файла
     * @return значения/value
     */
    private V get(K key) {
        V value = null;
        String file = key.toString();
        try (ObjectInputStream is = new ObjectInputStream(
                new BufferedInputStream(
                        new FileInputStream(new File(dir, file))))) {
            value = (V) is.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return value;
    }
}
