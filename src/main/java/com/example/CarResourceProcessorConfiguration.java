package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.webmvc.RepositoryLinksResource;
import org.springframework.data.rest.webmvc.RepositorySearchesResource;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceProcessor;

@Configuration
public class CarResourceProcessorConfiguration {
	
	@Autowired
	EntityLinks entityLinks;
	
	// adds links to individual car items
	// making it part of the _links node
	/*
	 * ...
	 * "list-cars": {
	 * 		"href": "http://localhost:PORT/cars/search/list"
	 * ...
	 */
	@Bean
	public ResourceProcessor<Resource<Car>> carProcessor() {
		return new ResourceProcessor<Resource<Car>>() {
			
			@Override
			public Resource<Car> process(Resource<Car> resource) {
				resource.add(new Link(entityLinks.linkFor(Car.class).toString() + "/search/list", "list-cars"));
			    return resource;
			}
			
		};
	}
	
	public ResourceProcessor<RepositorySearchesResource> carSearchesProcessor() {
		return new ResourceProcessor<RepositorySearchesResource>() {
			
			@Override
			public RepositorySearchesResource process(RepositorySearchesResource resource) {
				resource.add(new Link(entityLinks.linkFor(Car.class).toString() + "/search/list", "list"));
				return resource;
			}
			
		};
	}
	
	public ResourceProcessor<RepositoryLinksResource> carLinksProcessor() {
		return new ResourceProcessor<RepositoryLinksResource>() {
			
			@Override
			public RepositoryLinksResource process(RepositoryLinksResource resource) {
				resource.add(new Link(entityLinks.linkFor(Car.class).toString() + "/search/list", "list"));
				return resource;
			}
			
		};
	}
	
}
