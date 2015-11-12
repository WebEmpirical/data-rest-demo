package com.example.entities.projections

import org.springframework.data.rest.core.config.Projection

import com.example.entities.Category

@Projection(name="noDescription", types=[Category.class])
interface NoDescription {
	
	String getName()

}
