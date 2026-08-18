package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.bean.Country;
import com.example.demo.service.CountryService;

@RestController
public class CountryController {

	final CountryService countryService;

	CountryController(CountryService countryService) {
		this.countryService = countryService;
	}

	@GetMapping("/getCountries")
	public List<?> getCountries() {
		return countryService.getAllCountries();
	}

	// path param
	@GetMapping("/getCountry/{id}")
	public ResponseEntity<Country> getCountryById(@PathVariable(value = "id") int id) {
		Optional<Country> country = countryService.getCountryById(id);
		return ResponseEntity.of(country); // 200 + JSON body if present, 404 if empty
	}

	// query param
	// http://localhost:8080/getCountryByName?name=India
	// key -> name , value -> India
	@GetMapping("/getCountryByName")
	public ResponseEntity<Country> getCountryByName(@RequestParam(value = "name") String countryName) {
		try {
			Country country = countryService.getCountryByName(countryName);
			// return new ResponseEntity<Country>(HttpStatus.OK);
			return new ResponseEntity<>(country, HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		// return countryService.getCountryByName(countryName);
	}

	@PostMapping("/addCountry")
	public ResponseEntity<Country> addCountry(@RequestBody Country country) {
		Country con = countryService.addCountry(country);
		return new ResponseEntity<Country>(con, HttpStatus.CREATED);
	}

	@PutMapping("/updateCountry/{id}")
	public ResponseEntity<Country> updateCountry(@PathVariable int id, @RequestBody Country country) {
		Country existingCountry = countryService.getCountryById(id).orElseThrow();
		existingCountry.setCountryName(country.getCountryName());
		existingCountry.setCountryCapital(country.getCountryCapital());
		Country updatedCountry = countryService.updateCountry(existingCountry);
		return ResponseEntity.ok(updatedCountry);
	}

	@DeleteMapping("/deleteCountry/{id}")
	public AddResponse deleteCountry(@PathVariable(value = "id") int id) {
		return countryService.deleteCountry(id);
	}

}
