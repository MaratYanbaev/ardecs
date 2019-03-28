package com.ardecs.cache;

import com.ardecs.strategy.StrategyType;
import com.ardecs.user.User;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

/**
 * @author Marat Yanbaev (yanbaevms@gmail.com)
 * @since 25.03.2019
 */
public class MemoryCacheLFUTest {
    MemoryCache<Integer, User> memoryCache;

    @Before
    public void set() {
        memoryCache = new MemoryCache(StrategyType.LFU, 5);
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
    public void getValueAfterPutExtraUserThenTryGetZheny() {
        memoryCache.getFromCache(1);
        memoryCache.getFromCache(2);
        memoryCache.getFromCache(3);
        memoryCache.getFromCache(5);
        memoryCache.putToCache(6, new User(6, "Sveta"));

        User result = memoryCache.getFromCache(4);

        assertThat(result, is(nullValue()));
    }

    @Test
    public void getValueAfterPutExtraUserThenTryGetKaty() {
        for (int i = 1; i <= 5; i++) {
            for (int e = i; e <= 5; e++) {
                memoryCache.getFromCache(i);
            }
        }
        memoryCache.putToCache(6, new User(6, "Sveta"));

        User fifth = memoryCache.getFromCache(5);

        assertThat(fifth, is(nullValue()));
    }
}
