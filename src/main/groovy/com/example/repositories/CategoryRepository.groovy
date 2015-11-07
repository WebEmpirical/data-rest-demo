package com.example.repositories

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.rest.core.annotation.RepositoryRestResource

import com.example.entities.Category;;

@RepositoryRestResource(collectionResourceRel="categories", path="categories")
interface CategoryRepository extends JpaRepository<Category, Long> {
	
}
