package com.example.controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.rest.webmvc.RepositoryRestController
import org.springframework.hateoas.EntityLinks
import org.springframework.hateoas.Link
import org.springframework.hateoas.Resource
import org.springframework.hateoas.Resources
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody

import com.example.entities.Inventory
import com.example.services.InventoryService

@RepositoryRestController
class InventoryController {
	
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
	ResponseEntity<Resources<Resource>> inventoryByCategoryName(@RequestParam("name") String name, @RequestParam(value="page",required=false,defaultValue="1") String page, @RequestParam(value="size",required=false,defaultValue="20") String size) {
		Link link = new Link(entityLinks.linkFor(Inventory.class, "name","page","size").toString() + "/search/categoryName{?name,page,size}","categoryName")
		return new ResponseEntity<Resources<Resource>>(new Resources<Resource>(inventoryService.inventoryByCategoryName(name,Integer.parseInt(page),Integer.parseInt(size)), link),HttpStatus.OK)
	}
}
