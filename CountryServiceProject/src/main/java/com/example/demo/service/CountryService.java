package com.example.demo.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.example.demo.bean.Country;

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
		List countries = new ArrayList(countryIdMap.values()); // countryIdMap is hashmap when we use values() it will  be added into list													
		return countries;
	}

	
	public Country getCountryById(int id) {
		Country country = countryIdMap.get(id);
		return country;
	}
	
	
	public Country getCountryByName(String countryName) {
		Country country = null;
		
		for(int i : countryIdMap.keySet()) { //keySet() -> will return all the key from the HashMap
			if(countryIdMap.get(i).getCountryName().equals(countryName)) {
				country = countryIdMap.get(i); // whole object -> whole one entry
			}
		}
		return country;
	}
	
//	public Country addCountry(Country country) {
//		
//	}
	
}
