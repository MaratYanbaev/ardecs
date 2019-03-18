package com.ardecs.cache;

import com.ardecs.strategy.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * @author Marat Yanbaev (yanbaevms@gmail.com)
 * @since 17.03.2019
 */
public class FileCache<K, V extends Serializable> implements Cache<K, V> {
    private CacheStrategy<K> fileStrategy;
    private File dir;
    private String path;
    private String[] savedKeys;
    private int capacity = 5;

    public FileCache() {
        this.fileStrategy = new MRUStrategy<>();
        savedKeys = new String[0];
    }

    public FileCache(CacheStrategy<K> fileStrategy, String path) {
        this.fileStrategy = fileStrategy;
        savedKeys = new String[0];
        this.setPath(path);
    }

    @Override
    public Object[] putToCache(K key, V value) {
        Object[] o = null;
        if (!(hasEmptyPlace())) {
            writeDate(key, value);
            fileStrategy.putKey(key);
            o = new Object[2];
            K k = removeFromStrategy();
            o[0] = k;
            V v = removeFromCache(k);
            o[1] = v;
        } else {
            writeDate(key, value);
            fileStrategy.putKey(key);
        }
        return o;
    }

    /**
     * Метод записи данных/value
     * @param key - ключ/имя файла
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
        savedKeys = dir.list();
    }

    /**
     * Метод получения значения/value по ключу
     * @param key - ключ/имя файла
     * @return значения/value
     */
    private V get(K key) {
        V value = null;
        String file = key.toString();
        try (ObjectInputStream is = new ObjectInputStream(
                new BufferedInputStream(
                        new FileInputStream(new File(path, file))))) {
            value = (V) is.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return value;
    }

    @Override
    public V getFromCache(K key) {
        V value = null;
        if (isPresent(key)) {
            value = get(key);
            fileStrategy.updateLongOfKey(key);
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
            savedKeys = dir.list();
        }
        return value;
    }

    @Override
    public boolean isPresent(K key) {
        if (savedKeys.length > 0) {
            String sKey = key.toString();
            for (String k: savedKeys) {
                if (sKey.equals(k)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void clearCache() {
        int i = 0;
        int length = dir.list().length;
        while ((length != 0) && (i < length)) {
            try {
                Files.delete(Path.of(path, savedKeys[i]));
            } catch (IOException e) {
                e.printStackTrace();
            }
            i++;
        }
        savedKeys = dir.list();
        fileStrategy.clear();
    }

    @Override
    public boolean hasEmptyPlace() {
        return savedKeys.length < capacity;
    }

    @Override
    public K removeFromStrategy() {
        return fileStrategy.extractKey();
    }

    /**
     * Указать новый путь для записи файлов
     * возможно только, если кэш пустой.
     * @param path - путь
     * @return true, если кэш пустой, иначе false
     */
    public boolean setPath(String path) {
        if (getCacheSize() == 0) {
            this.dir = new File(path);
            this.path = path;
            return true;
        }
        return false;
    }

    @Override
    public CacheStrategy<K> getStrategy() {
        return fileStrategy;
    }

    @Override
    public int getCacheSize() {
        return savedKeys.length;
    }

    public int getCapacity() {
        return capacity;
    }

    @Override
    public boolean increaseCapacity(int capacity) {
        if (capacity > this.capacity) {
            this.capacity = capacity;
            return true;
        }
        return false;
    }

    @Override
    public boolean changeStrategy(CacheStrategy<K> type) {
        if (getCacheSize() == 0) {
            this.fileStrategy = type;
            return true;
        }
        return false;
    }
}
