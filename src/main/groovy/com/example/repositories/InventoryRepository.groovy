package com.example.repositories

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.query.Param
import org.springframework.data.rest.core.annotation.RepositoryRestResource
import org.springframework.data.rest.core.annotation.RestResource

import com.example.entities.Inventory

@RepositoryRestResource(collectionResourceRel="inventory", path="inventory")
interface InventoryRepository extends JpaRepository<Inventory, Long> {
	
	// finding inventory using the category id
	@RestResource(path="category",rel="category")
	Page findByCategory(@Param("id") long id, Pageable pageable)
	
}
