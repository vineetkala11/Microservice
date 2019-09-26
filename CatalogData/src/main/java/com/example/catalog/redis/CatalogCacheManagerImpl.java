package com.example.catalog.redis;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.example.catalog.model.CatalogDetail;

@Configuration
public class CatalogCacheManagerImpl implements CatalogCacheManager{

	public static final String CATALOG_TABLE = "CATALOG_DETAILS";
	public static final String CATALOG = "CATALOG_";
	
	private RedisUtil<CatalogDetail> redisUtilCatalogDetail;
	
	@Autowired
    public CatalogCacheManagerImpl(RedisUtil<CatalogDetail> redisUtilCatalogDetail) {
        this.redisUtilCatalogDetail = redisUtilCatalogDetail;
    }
	
	@Override
	public void cacheCatalogDetails(CatalogDetail catalogDetails) {
		redisUtilCatalogDetail.putMap(CATALOG_TABLE,catalogDetails.getId(),catalogDetails);
		//redisUtilCatalogDetail.putValue(CATALOG+catalogDetails.getId(), catalogDetails);
		redisUtilCatalogDetail.setExpire(CATALOG_TABLE,1,TimeUnit.HOURS);
	}

	public CatalogDetail getCatalogDetail(CatalogDetail catalogDetail) {
		return getCatalogDetail(catalogDetail.getId());
	}
	
	public Map<Object, CatalogDetail> getAllCatalogDetail() {
		return this.redisUtilCatalogDetail.getMapAsAll(CATALOG_TABLE);
	}
	
	public CatalogDetail getCatalogDetail(long id) {
		return (CatalogDetail) this.redisUtilCatalogDetail.getMapAsSingleEntry(CATALOG_TABLE,id);
	}
	
	public void removeCatalogDetail(long id) {
		this.redisUtilCatalogDetail.deleteFromMap(CATALOG_TABLE, id);
	}
}
