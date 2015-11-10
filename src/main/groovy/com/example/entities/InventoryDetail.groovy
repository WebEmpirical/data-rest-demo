package com.example.entities

import javax.persistence.Column;

class InventoryDetail {
	
	long id
	
	String item
	
	String name
	
	InventoryDetail(long id, String item, String name) {
		this.id = id
		this.item = item
		this.name = name
	}
	
	
	
}
