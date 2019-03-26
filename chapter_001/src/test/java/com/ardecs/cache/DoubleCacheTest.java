package com.ardecs.cache;

import com.ardecs.user.User;
import org.junit.Before;
import org.junit.Test;

import static com.ardecs.cache.CacheType.*;
import static com.ardecs.strategy.StrategyType.*;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.*;
import static org.hamcrest.Matchers.is;

public class DoubleCacheTest {
    private Cache<Integer, User> doubleCache;

    @Before
    public void set() {
        doubleCache = new CacheBuilder()
                .setPath("C:\\projects\\ardecs\\chapter_001\\src\\user")
                .setCache(DOUBLE)
                .setStrategy(LRU)
                .build();

        doubleCache.putToCache(1, new User(1, "Koly"));
        doubleCache.putToCache(2, new User(2, "Sergei"));
        doubleCache.putToCache(3, new User(3, "Oly"));
        doubleCache.putToCache(4, new User(4, "Zheny"));
        doubleCache.putToCache(5, new User(5, "Katy"));
        doubleCache.putToCache(6, new User(6, "Petr"));
        doubleCache.putToCache(7, new User(7, "Sveta"));
        doubleCache.putToCache(8, new User(8, "Oleg"));
        doubleCache.putToCache(9, new User(9, "Pushkin"));
        doubleCache.putToCache(10, new User(10, "Artur"));
    }

    @Test
    public void putExtraElementThenTryGetKoly() {
        doubleCache.putToCache(0, new User(0, "User"));

        User result = doubleCache.getFromCache(1);

        assertThat(result, is(nullValue()));
        doubleCache.clearCache();
    }
}