package com.example.catalog.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.catalog.model.CatalogDetail;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/catalog")
@Slf4j
public class CatalogController {

	@Autowired
	private Environment env;
	
	@GetMapping("/getAllCatalog")
	public ResponseEntity<List<CatalogDetail>> getAllCatalog() throws InterruptedException {
		log.info("Inside getCatalog Details");
		if(env.getProperty("server.port").equals("8002")) {
			Thread.sleep(3000);
		}
		List<CatalogDetail> details = new ArrayList<>();
		CatalogDetail cd = new CatalogDetail();
		cd.setItemName("Cricket Bat");
		cd.setItemCount(100);
		cd.setPort(env.getProperty("server.port"));
		CatalogDetail cd1 = new CatalogDetail();
		cd1.setItemName("Cricket Ball");
		cd1.setItemCount(350);
		cd1.setPort(env.getProperty("server.port"));
		CatalogDetail cd2 = new CatalogDetail();
		cd2.setItemName("Football");
		cd2.setItemCount(30);
		cd2.setPort(env.getProperty("server.port"));
		details.add(cd);details.add(cd1);details.add(cd2);
		log.info("Returing data : {}", details);
		return new ResponseEntity<>(details, HttpStatus.OK);
	}
}
