package com.example.demo.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UseController {

	//http://localhost:8080/users
	@GetMapping
	public String getUsers() {
		return "users";
	}
	
	//path parameter -> @PathVariable
	//http://localhost:8080/users/100
	@GetMapping(path = "/{userId}")
	public String getUser(@PathVariable String userId) {
		return "perticular user with userId";
	}
	
	//query parameter -> @RequestParam
	//http://localhost:8080/users/limit?page=1&limit=50
	//in postman we give these value like key value in Params section
	@GetMapping(path = "/limit")
	public String getUserLimit(@RequestParam( value= "page")int pageNo, 
							   @RequestParam( value= "limit")int limitNo) {
		return "PageNo "+ pageNo + " and Limit " + limitNo;
	}
	
	@PostMapping
	public String creatUsers() {
		return "create users";
	}
	
	@PutMapping
	public String updateUsers() {
		return "update users";
	}
	
	@DeleteMapping
	public String deleteUsers() {
		return "delete users";
	}
}
