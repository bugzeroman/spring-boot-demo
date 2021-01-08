package com.yuwen.spring.elasticsearch.entity;

import java.io.Serializable;

import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "province", type = "city")
public class City implements Serializable {

	private static final long serialVersionUID = -1L;

	/**
	 * 城市编号
	 */
	private Long id;

	/**
	 * 省份编号
	 */
	private String provinceName;

	/**
	 * 城市名称
	 */
	private String cityname;

	/**
	 * 描述
	 */
	private String description;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public String getCityname() {
		return cityname;
	}

	public void setCityname(String cityname) {
		this.cityname = cityname;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "City [id=" + id + ", provinceName=" + provinceName + ", cityname=" + cityname + ", description="
				+ description + "]";
	}
}