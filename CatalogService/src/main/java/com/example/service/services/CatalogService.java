package com.example.service.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.service.feignClient.CatalogServiceProxy;
import com.example.service.model.CatalogDetail;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CatalogService {

	@Autowired
	private CatalogServiceProxy serviceProxy;
	
	@HystrixCommand(fallbackMethod="fallback", commandProperties = {
			   @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "30000")
	})
	public List<CatalogDetail> getCatalogUsingRestTemplate(){
		List<CatalogDetail> details = serviceProxy.getCatalogList();
		return details;
	}
	
	public List<CatalogDetail> fallback() {
		log.info("Inside fallback !!!!");
		List<CatalogDetail> details = new ArrayList<>();
		CatalogDetail cd = new CatalogDetail();
		cd.setItemName("NA");
		cd.setItemCount(0);
		details.add(cd);
		return details;
	}
}
