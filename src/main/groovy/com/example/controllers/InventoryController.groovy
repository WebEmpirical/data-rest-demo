package com.example.controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.rest.webmvc.RepositoryRestController
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

import com.example.entities.Inventory
import com.example.services.InventoryService

@RepositoryRestController
class InventoryController implements ResourceProcessor<Resource<Inventory>> {
	
	EntityLinks entityLinks
	InventoryService inventoryService
	
	@Autowired
	InventoryController(EntityLinks entityLinks, InventoryService inventoryService) {
		super()
		this.entityLinks = entityLinks
		this.inventoryService = inventoryService
	}
	
	@RequestMapping(value="/inventory/search/categoryName",method=RequestMethod.GET)
	@ResponseBody
	ResponseEntity<PagedResources> inventoryByCategoryName(
			@RequestParam("name") String name,
			@RequestParam(value="page",required=false,defaultValue="1") String page,
			@RequestParam(value="size",required=false,defaultValue="20") String size,
			@RequestParam(value="sort",required=false,defaultValue="_NONE_") String sort
	) {
		Link link = new Link(entityLinks.linkFor(Inventory.class, "name","page","size").toString() + "/search/categoryName{?name,page,size}","categoryName")
		def result = inventoryService.inventoryByCategoryName(name,Integer.parseInt(page),Integer.parseInt(size),sort)
		return new ResponseEntity<PagedResources>(new PagedResources(result[0], result[1], link),HttpStatus.OK)
	}

	@Override
	public Resource<Inventory> process(Resource<Inventory> resource) {
		// TODO Auto-generated method stub
		return resource;
	}
}
