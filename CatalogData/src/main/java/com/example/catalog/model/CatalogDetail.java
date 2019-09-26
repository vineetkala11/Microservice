package com.example.catalog.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class CatalogDetail implements Serializable{

	private static final long serialVersionUID = -2532133327151926595L;
	private long id;
	private String itemName;
	private int itemCount;
	private String port;
}
