package com.example.entities.projections

import org.springframework.data.rest.core.config.Projection

import com.example.entities.Category
import com.example.entities.Inventory

@Projection(name="inlineInventory", types=[Category.class])
interface InlineInventory {
	
	long getId()
	String getName()
	String getDescription()
	List<Inventory> getInventory()

}
