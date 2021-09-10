package com.yuwen.framework.demo;

import redis.clients.jedis.Jedis;

/**
 * 创建Jedis连接，并且检测连通性
 *
 */
public class JedisDemo {
	public static void main(String[] args) {
		// 1.创建Jedis连接
		String host = "10.21.13.14";
		int port = 6379;
		Jedis jedis = new Jedis(host, port);
		// 2.检测连通性
		String result = jedis.ping();
		System.out.println("result=" + result);

		// 3.关闭Jedis连接
		jedis.close();
	}
}
