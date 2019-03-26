package com.ardecs.cache;

import com.ardecs.user.User;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.*;

public class MemoryCacheTest {
    MemoryCache<Integer, User> memoryCache;

    @Before
    public void set() {
        memoryCache = new MemoryCache<>();
        User one = new User(1, "Koly");
        User two = new User(2, "Sergei");
        User three = new User(3, "Oly");
        User four = new User(4, "Zheny");
        User five = new User(5, "Katy");
        memoryCache.putToCache(one.getId(), one);
        memoryCache.putToCache(two.getId(), two);
        memoryCache.putToCache(three.getId(), three);
        memoryCache.putToCache(four.getId(), four);
        memoryCache.putToCache(five.getId(), five);
    }

    @Test
    public void getValueOlyAfterPutExtraUserThenTryAgainGetOly() {
        memoryCache.getFromCache(3);
        memoryCache.putToCache(6, new User(6, "Sveta"));

        User result = memoryCache.getFromCache(3);

        assertThat(result, is(nullValue()));
    }

    @Test
    public void putToCacheExtraUserAfterTryFindKatyThenNull() {
        memoryCache.putToCache(6, new User(6, "Sveta"));

        User fifth = memoryCache.getFromCache(5);

        assertThat(fifth, is(nullValue()));
    }
}