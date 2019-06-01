
package com.paul.consumer.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
public class ConsumerController{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ConsumerController.class);
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private LoadBalancerClient loadBalancerClient;
	
	@GetMapping(value="/queryUser/{id}")
	public String queryUser(@PathVariable String id){
		return this.restTemplate.getForObject("http://microservice-provider-one/queryUserById/"+id, String.class);
	}
	
	@GetMapping(value="/log-instance")
	public void logInstance(){
		ServiceInstance serviceInstance = this.loadBalancerClient.choose("microservice-provider-one");
		ConsumerController.LOGGER.error("{}:{}:{}",serviceInstance.getServiceId(),serviceInstance.getHost(),serviceInstance.getPort());
	}
}