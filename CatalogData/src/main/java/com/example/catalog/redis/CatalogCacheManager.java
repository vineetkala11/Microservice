package com.example.catalog.redis;

import java.util.Map;

import com.example.catalog.model.CatalogDetail;

public interface CatalogCacheManager {
	void cacheCatalogDetails(CatalogDetail catalogDetails);
	CatalogDetail getCatalogDetail(CatalogDetail catalogDetail);
	CatalogDetail getCatalogDetail(long id);
	void removeCatalogDetail(long id);
	Map<Object, CatalogDetail> getAllCatalogDetail();
}
