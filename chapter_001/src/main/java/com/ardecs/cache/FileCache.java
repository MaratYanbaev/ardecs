package com.ardecs.cache;

import com.ardecs.strategy.*;

import java.io.*;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
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
    private int capacity = 5;
    private String[] savedKeys = new String[0];

    public FileCache() {
        this.fileStrategy = new MRUStrategy<>();
        setPath(null);
    }

    public FileCache(String path) {
        this.fileStrategy = new MRUStrategy<>();
        setPath(path);
    }

    public FileCache(StrategyType memoryStrategy, int capacity, String path) {
        this.fileStrategy = getStrategy(memoryStrategy);
        this.capacity = capacity;
        setPath(path);
    }

    @Override
    public Object[] putToCache(K key, V value) throws NullPointerException {
        if ((value == null) && (key == null)) {
            throw new NullPointerException("value and key can't be null");
        }
        Object[] o = null;
        if (!(hasEmptyPlace())) {
            o = new Object[2];
            K k = extractFromStrategy();
            o[0] = k;
            V v = removeFromCache(k);
            o[1] = v;
            writeDate(key, value);
        } else {
            writeDate(key, value);
        }
        return o;
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
            fileStrategy.removeFromStrategy(key);
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
    public boolean hasEmptyPlace() {
        return savedKeys.length < capacity;
    }

    @Override
    public K extractFromStrategy() {
        return fileStrategy.extractKey();
    }

    @Override
    public void clearCache() {
        if (savedKeys.length > 0) {
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
    }

    @Override
    public boolean changeStrategy(StrategyType type) {
        if (getCacheSize() == 0) {
            this.fileStrategy = getStrategy(type);
            return true;
        }
        return false;
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
    public CacheStrategy<K> getStrategy() {
        return fileStrategy;
    }

    @Override
    public int getCacheSize() {
        return savedKeys.length;
    }

    @Override
    public int getCapacity() {
        return capacity;
    }

    /**
     * Указать новый путь для записи файлов
     * возможно только, если файловый кэш пустой.
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
                        new FileInputStream(new File(dir, file))))) {
            value = (V) is.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return value;
    }
}
