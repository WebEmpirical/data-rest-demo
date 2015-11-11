package com.example.dto

import org.springframework.hateoas.PagedResources.PageMetadata;

// custom page object that extends PageMetadata
// PageMetadata is what is used in PagedResources to return page information
class Page extends PageMetadata {
	
	long size
	long totalElements
	long totalPages
	long number

	Page(long size, long totalElements, long number) {
		super()
		this.size = size
		this.totalElements = totalElements
		this.number = number
		// we will calculate this value and return the totalPages
		this.totalPages = (Long)Math.ceil( ( ( this.totalElements - (this.totalElements % this.size) ) / this.size ) + ( (this.totalElements % this.size > 0) ? 1 : 0 ) )
	}	

}
