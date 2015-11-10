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

import com.example.entities.Inventory;

@Configuration
public class InventoryResourceProcessorConfiguration {
	
	@Autowired
	EntityLinks entityLinks;
	
	@Bean
	public ResourceProcessor<Resource<Inventory>> resource() {
		return new ResourceProcessor<Resource<Inventory>>() {

			@Override
			public Resource<Inventory> process(Resource<Inventory> resource) {
				return null;
			}
			
		};
		
	}
	
	@Bean
	public ResourceProcessor<RepositorySearchesResource> repositorSearchesProcessor() {
		return new ResourceProcessor<RepositorySearchesResource>() {
			
			@Override
			public RepositorySearchesResource process(RepositorySearchesResource resource) {
				resource.add(new Link(entityLinks.linkFor(Inventory.class, "name").toString() + "/search/categoryName{?name}", "categoryName"));
				return resource;
			}
			
		};
	}
	
	@Bean
	public ResourceProcessor<RepositoryLinksResource> repositorLinksProcessor() {
		return new ResourceProcessor<RepositoryLinksResource>() {
			
			@Override
			public RepositoryLinksResource process(RepositoryLinksResource resource) {
				return null;
			}
			
		};
	}

}
