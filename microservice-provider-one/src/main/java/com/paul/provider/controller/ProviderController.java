package com.paul.provider.controller;

import java.util.Random;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProviderController {
	
	@GetMapping(value="/queryUserById/{id}")
	public String queryUserById(@PathVariable String id){
		Random random =  new Random();
		int num = random.nextInt();
		return id+"==="+num;
	}
}
