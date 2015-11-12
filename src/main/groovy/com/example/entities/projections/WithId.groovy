package com.example.entities.projections

import org.springframework.data.rest.core.config.Projection

import com.example.entities.Category

@Projection(name="withId", types=[Category.class])
interface WithId {
	
	long getId()
	String getName()
	String getDescription()

}
