package com.example

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
import org.springframework.web.bind.annotation.ResponseBody

@RepositoryRestController
class CarController {
	
	EntityLinks entityLinks
	CarRepository carRepository
	
	@Autowired
	public CarController(CarRepository carRepository, EntityLinks entityLinks) {
		this.carRepository = carRepository
		this.entityLinks = entityLinks
	}	
	
	/*@RequestMapping(value="/cars/search/list",method=RequestMethod.GET)
	@ResponseBody
	List<Car> list() {
		return carRepository.findAll()
	}*/
	
	@RequestMapping(value="/cars/search/list",method=RequestMethod.GET)
	@ResponseBody
	ResponseEntity<Resources<Resource>> list() {
		Link link = new Link(entityLinks.linkFor(Car.class).toString() + "/search/list")
		return new ResponseEntity<Resources<Resource>>(new Resources<Resource>(carRepository.findAll(), link),HttpStatus.OK)
	}
}