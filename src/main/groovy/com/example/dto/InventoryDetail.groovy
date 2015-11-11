package com.example.dto

class InventoryDetail {
	
	long id
	
	long categoryId
	
	String categoryName
	
	String item
	
	String itemDescription
	
	long price
	
	long onHand

	InventoryDetail(long id, long categoryId, String categoryName, String item, String itemDescription,
			long price, long onHand) {
		super()
		this.id = id
		this.categoryId = categoryId
		this.categoryName = categoryName
		this.item = item
		this.itemDescription = itemDescription
		this.price = price
		this.onHand = onHand
	}
}
