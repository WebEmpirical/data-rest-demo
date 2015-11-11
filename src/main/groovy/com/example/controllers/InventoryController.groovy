package com.example.controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import org.springframework.data.rest.webmvc.RepositoryRestController
import org.springframework.data.web.PagedResourcesAssembler
import org.springframework.hateoas.EntityLinks
import org.springframework.hateoas.Link
import org.springframework.hateoas.PagedResources
import org.springframework.hateoas.Resource
import org.springframework.hateoas.ResourceProcessor
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody

import com.example.dto.InventoryDetail
import com.example.entities.Inventory
import com.example.services.InventoryService

@RepositoryRestController
class InventoryController implements ResourceProcessor<Resource<Inventory>> {
	
	// makes building links easier and more flexible
	EntityLinks entityLinks
	InventoryService inventoryService
	PagedResourcesAssembler<InventoryDetail> pagedResourcesAssembler
	
	@Autowired
	InventoryController(EntityLinks entityLinks, InventoryService inventoryService, PagedResourcesAssembler<InventoryDetail> pagedResourcesAssembler) {
		super()
		this.entityLinks = entityLinks
		this.inventoryService = inventoryService
		this.pagedResourcesAssembler = pagedResourcesAssembler
	}
	
	// the value should match our /search/... api since we added this to the RepositorySearchesResource in our config class
	// name is required, the other request params are optional with default values
	// using the PagedResources here so that we can have _embedded, _links, and page as a result
	@RequestMapping(value="/inventory/search/categoryName",method=RequestMethod.GET)
	@ResponseBody
	ResponseEntity<PagedResources> inventoryByCategoryName(
			@RequestParam("name") String name,
			@RequestParam(value="page",required=false,defaultValue="1") String page,
			@RequestParam(value="size",required=false,defaultValue="20") String size,
			@RequestParam(value="sort",required=false,defaultValue="_NONE_") String sort,
			Pageable pageable
	) {
		// this is the link that will be part of our HAL/JSON documentation
		Link link = new Link(entityLinks.linkFor(Inventory.class, "name","page","size","sort").toString() + "/search/categoryName{?name,page,size,sort}").withSelfRel()
		// we're actually returning 2 values from the service, assign them to the temp variable for later use
		def (resultList,totalRecords) = inventoryService.inventoryByCategoryName(name,Integer.parseInt(page),Integer.parseInt(size),sort)
		// returning the response as a PagedResources using the PagedResourcesAssembler to build the first,pref,next,last _links
		return new ResponseEntity<PagedResources>(pagedResourcesAssembler.toResource(new PageImpl<InventoryDetail>(resultList,pageable,totalRecords),link),HttpStatus.OK)
	}
	
	// this is where we would put _links for individual "items" to be embedded and made available from them
	@Override
	public Resource<Inventory> process(Resource<Inventory> resource) {
		// TODO Auto-generated method stub
		return resource;
	}
}
