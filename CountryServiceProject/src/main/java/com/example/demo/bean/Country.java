package com.example.demo.bean;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "country")
public class Country {

	@Id
	@Column(name = "id")
	Integer id;

	@Column(name = "country_name")
	String countryName;

	@Column(name = "capital")
	String countryCapital;

	public Country() {

	}

	public Country(Integer id, String countryName, String countryCapital) {

		this.id = id;
		this.countryName = countryName;
		this.countryCapital = countryCapital;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public String getCountryCapital() {
		return countryCapital;
	}

	public void setCountryCapital(String countryCapital) {
		this.countryCapital = countryCapital;
	}

	// Integer id;

	/*
	 * why changing int id → Integer id fixed the 400
	 * 
	 * • Java primitive types (int, boolean, long, ...) cannot hold null. Wrapper
	 * types (Integer, Boolean, Long, ...) can. • When Jackson deserializes JSON: •
	 * If the JSON explicitly contains "id": null, Jackson will attempt to assign
	 * null to the Java property. • Assigning null to a primitive int is impossible,
	 * so Jackson (by default) throws a
	 * MismatchedInput/HttpMessageNotReadableException and Spring returns HTTP 400.
	 * Your log showed exactly that. • With Integer id, Jackson can store null in
	 * the field, so deserialization succeeds and no 400 is caused by that mapping.
	 */

	/*
	 * CREATE DATABASE my_db; use my_db; create table country( id INT AUTO_INCREMENT
	 * PRIMARY KEY, capital varchar(255) NOT NULL, country_name varchar(255) not
	 * null); desc country;
	 * 
	 * insert into country values(1, "Delhi", "India"); insert into country
	 * values(2, "Washington", "USA"); insert into country values(3, "London",
	 * "UK");
	 * select * from country;
	 */

}
