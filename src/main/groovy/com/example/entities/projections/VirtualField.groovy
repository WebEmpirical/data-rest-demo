package com.example.entities.projections

import org.springframework.beans.factory.annotation.Value
import org.springframework.data.rest.core.config.Projection

import com.example.entities.Inventory

@Projection(name="virtualField", types=[Inventory.class])
interface VirtualField {
	
	@Value("1@#{target.price} ea.")
	String getVirtualPrice()

}
