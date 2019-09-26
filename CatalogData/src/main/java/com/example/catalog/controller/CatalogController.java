package com.example.catalog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.catalog.model.CatalogDetail;
import com.example.catalog.service.CatalogService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/catalog")
@Slf4j
public class CatalogController {

	@Autowired
	private Environment env;
	
	@Autowired
	private CatalogService catalogService; 
	
	@GetMapping("/getAllCatalog")
	//@Cacheable("catalog_data")
	public List<CatalogDetail> getAllCatalog() throws InterruptedException {
		log.info("Inside getCatalog Details .....");
		if(env.getProperty("server.port").equals("8002")) {
			Thread.sleep(3000);
		}
		return this.catalogService.getAllCatalogDetails();
	}
	
	@GetMapping("/getCatalog/{id}")
	//@Cacheable("catalog_data")
	public CatalogDetail getCatalog(@PathVariable(value="id") long id ) throws InterruptedException {
		log.info("Inside getCatalog Details .....");
		if(env.getProperty("server.port").equals("8002")) {
			Thread.sleep(3000);
		}
		return this.catalogService.getCatalogDetail(id);
	}
	
	@GetMapping("/remove/{id}")
	public void removeCatalog(@PathVariable(value="id") long id ) throws InterruptedException {
		log.info("Inside removeCatalog .....");
		if(env.getProperty("server.port").equals("8002")) {
			Thread.sleep(3000);
		}
		this.catalogService.removeData(id);
	}
	
	@PostMapping("/add")
	public void addData(@RequestBody CatalogDetail catalogDetails) {
		this.catalogService.addData(catalogDetails);
	}
	
	@PutMapping("/update")
	public void updateData(@RequestBody CatalogDetail catalogDetails) {
		this.catalogService.updateData(catalogDetails);
	}
}
