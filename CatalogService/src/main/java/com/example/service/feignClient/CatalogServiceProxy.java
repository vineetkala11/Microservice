package com.example.service.feignClient;

import java.util.List;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.service.model.CatalogDetail;
import com.example.service.ribbon.RibbonConfiguration;

import feign.Headers;

@FeignClient(name="catalog-data")
@RibbonClient(name="catalog-data", configuration = RibbonConfiguration.class)
public interface CatalogServiceProxy {

	@Headers("Content-Type: application/json")
	@GetMapping("/catalog/getAllCatalog")
    public List<CatalogDetail> getCatalogList();
}
