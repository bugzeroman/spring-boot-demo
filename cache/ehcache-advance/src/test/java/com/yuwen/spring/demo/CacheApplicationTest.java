package com.yuwen.spring.demo;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.Cache;
import org.springframework.cache.ehcache.EhCacheCache;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.test.context.junit4.SpringRunner;

import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CacheApplicationTest {
	@Autowired
	private EhCacheCacheManager cacheManager;

	/**
	 * cacheManager获取cache,通过cache获取EhCacheCache, 通过EhCacheCache获取Ehcache
	 */
	@Test
	public void getEhCache2Way() throws Exception {
		Cache cache = cacheManager.getCache("userstore");
		System.out.println(cache.getClass());
		if (cache instanceof EhCacheCache) {
			EhCacheCache ehCacheCache = (EhCacheCache) cache;
			Ehcache ehcache = ehCacheCache.getNativeCache();
			System.out.println("ehCacheCache=" + ehCacheCache);
			System.out.println("ehcache=" + ehcache);
		}
	}

	/**
	 * cacheManager获取cache,通过cache直接获取Ehcache
	 */
	@Test
	public void getEhCache1Way() throws Exception {
		Cache cache = cacheManager.getCache("userstore");
		System.out.println(cache.getClass());
		Object nativeCache = cache.getNativeCache();
		System.out.println("nativeCache=" + nativeCache);
		if (nativeCache instanceof Ehcache) {
			Ehcache ehcache = (Ehcache) nativeCache;
			System.out.println("ehcache=" + ehcache);
			System.out.println("ehcache.getSize()=" + ehcache.getSize());
		}
	}

	/**
	 * 获取缓存的所有key，通过Ehcache的getKeys方法
	 */
	@Test
	public void getEhCacheVaules() throws Exception {
		Cache cache = cacheManager.getCache("userstore");
		cache.put("key1", "value1");
		cache.put("key2", "value2");
		cache.put("key3", "value4");
		Object nativeCache = cache.getNativeCache();
		if (nativeCache instanceof Ehcache) {
			Ehcache ehcache = (Ehcache) nativeCache;
			Element element = ehcache.get("key1");
			System.out.println("key1=" + element);

			List keys = ehcache.getKeys();
			System.out.println(keys);
			System.out.println(keys.getClass());
			System.out.println(keys.get(0).getClass());
			List<String> keys2 = ehcache.getKeys();
			System.out.println(keys2);
		}
	}

}
