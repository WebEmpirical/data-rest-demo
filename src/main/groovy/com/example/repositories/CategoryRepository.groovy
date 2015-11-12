package com.example.repositories

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.data.rest.core.annotation.RepositoryRestResource
import org.springframework.data.rest.core.annotation.RestResource

import com.example.entities.Category

//@RepositoryRestResource(collectionResourceRel="categories", path="categories",excerptProjection=WithId.class)
//@RepositoryRestResource(collectionResourceRel="categories", path="categories",excerptProjection=InlineInventory.class)
@RepositoryRestResource(collectionResourceRel="categories", path="categories")
interface CategoryRepository extends JpaRepository<Category, Long> {
	
	// case-insensitive query using jpa query method
	@RestResource(path="nameStartsWith",rel="nameStartsWith")
	Page findByNameStartsWithIgnoreCase(@Param("name") String name, Pageable pageable)
	
	// same as above, this time using query annotation
	@RestResource(path="nameStartsWithQuery",rel="nameStartsWithQuery")
	@Query("select c from Category c where upper(c.name) like upper(:name+'%')")
	Page queryByNameStartsWithIgnoreCase(@Param("name") String name, Pageable pageable)
	
}
