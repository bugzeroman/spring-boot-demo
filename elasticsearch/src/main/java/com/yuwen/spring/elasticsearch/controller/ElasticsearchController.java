package com.yuwen.spring.elasticsearch.controller;

import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.web.bind.annotation.DeleteMapping;
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
	private ElasticsearchRestTemplate esTemplate;

	@PostMapping("city")
	public void createCity(@RequestBody City city) {
		System.out.println("create city=" + city);
		esTemplate.save(city);
	}

	@GetMapping("city/id/{id}")
	public City getCityById(@PathVariable("id") String id) {
		City city = esTemplate.get(id, City.class);
		System.out.println("id=" + id + ", query city=" + city);
		return city;
	}

	/**
	 * 复杂查询，未实现
	 */
	@GetMapping("city/name/{name}")
	public City getCityByName(@PathVariable String name) {

		Query query = new NativeSearchQueryBuilder().withQuery(QueryBuilders.termQuery("cityName", "常州")).build();
		IndexCoordinates index = esTemplate.getIndexCoordinatesFor(City.class);
		SearchHits<City> result = esTemplate.search(query, City.class, index);
		System.out.println("name=" + name + ", query result=" + result);
		return result.getSearchHit(0).getContent();
	}

	@DeleteMapping("city/id/{id}")
	public void deleteCityById(@PathVariable String id) {
		String result = esTemplate.delete(id, City.class);
		System.out.println("id=" + id + ", delete result=" + result);
	}

}
