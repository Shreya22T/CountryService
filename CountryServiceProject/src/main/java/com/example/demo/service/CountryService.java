package com.example.demo.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.bean.Country;
import com.example.demo.controller.AddResponse;

@Service
public class CountryService {

	static HashMap<Integer, Country> countryIdMap;

	public CountryService() {
		countryIdMap = new HashMap<Integer, Country>();

		Country indiacountry = new Country(1, "India", "Delhi");
		Country usaCountry = new Country(2, "USA", "Washington");
		Country ukCountry = new Country(3, "UK", "London");

		countryIdMap.put(1, indiacountry);
		countryIdMap.put(2, usaCountry);
		countryIdMap.put(3, ukCountry);
	}

	public List getAllCountries() {
		List countries = new ArrayList(countryIdMap.values()); // countryIdMap is hashmap when we use values() it will
																// be added into list
		return countries;
	}

	//path param
	public Country getCountryById(int id) {
		Country country = countryIdMap.get(id);
		return country;
	}

	//query param
	public Country getCountryByName(String countryName) {
		Country country = null;
		
		
	//Integer id;
		
	/*why changing int id → Integer id fixed the 400

	•  Java primitive types (int, boolean, long, ...) cannot hold null. Wrapper types (Integer, Boolean, Long, ...) can.
	•  When Jackson deserializes JSON:
	•  If the JSON explicitly contains "id": null, Jackson will attempt to assign null to the Java property.
	•  Assigning null to a primitive int is impossible, so Jackson (by default) throws a MismatchedInput/HttpMessageNotReadableException and Spring returns HTTP 400. Your log showed exactly that.
	•  With Integer id, Jackson can store null in the field, so deserialization succeeds and no 400 is caused by that mapping.
*/

		for (int i : countryIdMap.keySet()) { // keySet() -> will return all the key from the HashMap
			if (countryIdMap.get(i).getCountryName().equals(countryName)) {
				country = countryIdMap.get(i); // whole object -> whole one entry
			}
		}
		return country;
	}

	public Country addCountry(Country country) {
		country.setId(getMaxId());
		countryIdMap.put(country.getId(), country);
		return country;

	}

	//path param
	public Country updateCountry(Country country) {
		if (country.getId() > 0) {
			countryIdMap.put(country.getId(), country);
		}
		return country;
	}

	//path param
	public AddResponse deleteCountry(int id) {
		countryIdMap.remove(id);
		AddResponse res = new AddResponse();
		res.setMsg("Country deleted...");
		res.setId(id);
		return res; // here it will be automatically convert into json object from spring boot
					// framework (in others we need to import jackson api do manually)
	}

	// utility methods to get max id
	public static int getMaxId() {
		int max = 0;
		for (int id : countryIdMap.keySet()) {
			if (max <= id) {
				max = id;
			}
		}

		return max + 1;
	}

}
