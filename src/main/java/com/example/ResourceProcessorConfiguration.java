package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.webmvc.RepositoryLinksResource;
import org.springframework.data.rest.webmvc.RepositorySearchesResource;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceProcessor;

import com.example.entities.Inventory;

@Configuration
public class ResourceProcessorConfiguration {
	
	// use this to build links that are flexible and portable
	@Autowired
	EntityLinks entityLinks;
	
	// this ResourceProcessor is used to add links to the various entities to the /search/... api
	// we can use this as a global way of injecting links for any entity
	@Bean
	public ResourceProcessor<RepositorySearchesResource> repositorSearchesProcessor() {
		return new ResourceProcessor<RepositorySearchesResource>() {
			
			@Override
			public RepositorySearchesResource process(RepositorySearchesResource resource) {
				resource.add(new Link(entityLinks.linkFor(Inventory.class, "name","page","size","sort").toString() + "/search/categoryName{?name,page,size,sort}", "categoryName"));
				return resource;
			}
			
		};
	}
	
	// this ResourceProcessor is used to add links to the root HAL/JSON document
	// not specific to an entity, can be http://my-domain.com/api/method...
	@Bean
	public ResourceProcessor<RepositoryLinksResource> repositorLinksProcessor() {
		return new ResourceProcessor<RepositoryLinksResource>() {
			
			@Override
			public RepositoryLinksResource process(RepositoryLinksResource resource) {
				return resource;
			}
			
		};
	}

}
