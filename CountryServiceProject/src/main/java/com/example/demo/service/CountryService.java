package com.example.demo.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.example.demo.bean.Country;
import com.example.demo.controller.AddResponse;

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

	public Country getCountryById(int id) {
		Country country = countryIdMap.get(id);
		return country;
	}

	public Country getCountryByName(String countryName) {
		Country country = null;

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

	public Country updateCountry(Country country) {
		if (country.getId() > 0) {
			countryIdMap.put(country.getId(), country);
		}
		return country;
	}

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
