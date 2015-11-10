package com.example.dto

import org.springframework.hateoas.PagedResources.PageMetadata;

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
		this.totalPages = (Long)Math.ceil( ( ( this.totalElements - (this.totalElements % this.size) ) / this.size ) + ( (this.totalElements % this.size > 0) ? 1 : 0 ) )
	}	

}
