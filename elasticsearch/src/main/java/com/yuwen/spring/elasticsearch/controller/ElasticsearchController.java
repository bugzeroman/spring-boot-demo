package com.yuwen.spring.elasticsearch.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.query.GetQuery;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yuwen.spring.elasticsearch.entity.City;

@RequestMapping("province")
@RestController
public class ElasticsearchController {

	@Autowired
	private ElasticsearchOperations esClient;

	@PostMapping("city")
	public void createCity(@RequestBody City city) {
		System.out.println("create city=" + city);
		esClient.save(city);
	}

	@GetMapping("city/{id}")
	public City getCityById(@PathVariable("id") Long id) {
		City city = esClient.queryForObject(GetQuery.getById(id.toString()), City.class);
		System.out.println("id=" + id + ", query city=" + city);
		return city;
	}

}
