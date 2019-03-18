package com.ardecs.cache;

import com.ardecs.strategy.StrategyType;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.*;

public class DoubleCacheTest {
    private DoubleCache<Integer, User> doubleCache = new DoubleCache<>();
    private DoubleCache.CacheBuilder<Integer, User> builder = new DoubleCache.CacheBuilder<>(doubleCache);

    @Before
    public void set() {
        User one = new User(1, "Koly");
        User two = new User(2, "Sergei");
        User three = new User(3, "Oly");
        User four = new User(4, "Zheny");
        User five = new User(5, "Katy");
        doubleCache.putToCache(one.getId(), one);
        doubleCache.putToCache(two.getId(), two);
        doubleCache.putToCache(three.getId(), three);
        doubleCache.putToCache(four.getId(), four);
        doubleCache.putToCache(five.getId(), five);
    }

    private void addFiveElement() {
        doubleCache.putToCache(6, new User(6, "Sveta"));
        doubleCache.putToCache(7, new User(7, "Marat"));
        doubleCache.putToCache(8, new User(8, "Azat"));
        doubleCache.putToCache(9, new User(9, "Kosty"));
        doubleCache.putToCache(10, new User(10, "Oleg"));
    }

    @Test
    public void putToCacheExtraUserAfterTryFindKatyThenNull() {
        doubleCache.putToCache(6, new User(6, "Sveta"));

        User fifth = doubleCache.getValue(5);

        assertThat(fifth, is(nullValue()));
    }

    @Test
    public void getValueOlyAfterPutExtraUserThenTryAgainGetOly() {
        doubleCache.getValue(3);
        doubleCache.putToCache(6, new User(6, "Sveta"));

        User result = doubleCache.getValue(3);

        assertThat(result, is(nullValue()));
    }

    @Test
    public void setLruStrategyAddFileCacheAddSixExtraUserThenTryGetLRU() {
        doubleCache.clearCache();
        builder.changeStrategy(StrategyType.LRU)
                .addFileCache("C:\\projects\\ardecs\\chapter_001\\src\\user");
        this.set();
        this.addFiveElement();

        doubleCache.putToCache(11, new User(11, "Sergei"));
        User result = doubleCache.getValue(1);

        doubleCache.clearCache();
        assertThat(result, is(nullValue()));
    }

    /**
     * Просматриваем первые девять элементов из десяти имеющихся,
     * затем добавляем одинадцатый и далее пытаемся получить
     * элемент №10.
     */
    @Test
    public void setLfuStrategyAddFileCacheAddFiveExtraElementsThenTryGetLFU() {
        doubleCache.clearCache();
        builder.changeStrategy(StrategyType.LFU)
                .addFileCache("C:\\projects\\ardecs\\chapter_001\\src\\user");
        this.set();
        this.addFiveElement();

        for (int i = 1; i < 10; i++) {
            doubleCache.getValue(i);
        }
        doubleCache.putToCache(11, new User(11, "Sergei"));
        User result = doubleCache.getValue(10);

        doubleCache.clearCache();
        assertThat(result, is(nullValue()));
    }
}