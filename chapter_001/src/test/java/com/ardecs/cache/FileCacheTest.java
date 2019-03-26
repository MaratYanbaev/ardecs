package com.ardecs.cache;

import com.ardecs.user.User;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.*;

public class FileCacheTest {

    FileCache<Integer, User> fileCache;

    @Before
    public void set() {
        fileCache = new FileCache<>("C:\\projects\\ardecs\\chapter_001\\src\\user");
        User one = new User(1, "Koly");
        User two = new User(2, "Sergei");
        User three = new User(3, "Oly");
        User four = new User(4, "Zheny");
        User five = new User(5, "Katy");
        fileCache.putToCache(one.getId(), one);
        fileCache.putToCache(two.getId(), two);
        fileCache.putToCache(three.getId(), three);
        fileCache.putToCache(four.getId(), four);
        fileCache.putToCache(five.getId(), five);
    }

    @Test
    public void getValueOlyAfterPutExtraUserThenTryAgainGetOly() {
        fileCache.getFromCache(3);
        fileCache.putToCache(6, new User(6, "Sveta"));

        User result = fileCache.getFromCache(3);

        assertThat(result, is(nullValue()));
        fileCache.clearCache();
    }

    @Test
    public void putToCacheExtraUserAfterTryFindKatyThenNull() {
        fileCache.putToCache(6, new User(6, "Sveta"));

        User fifth = fileCache.getFromCache(5);

        assertThat(fifth, is(nullValue()));
        fileCache.clearCache();
    }
}