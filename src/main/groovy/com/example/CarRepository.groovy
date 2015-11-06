package com.example

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.query.Param
import org.springframework.data.rest.core.annotation.RepositoryRestResource
import org.springframework.data.rest.core.annotation.RestResource



@RepositoryRestResource(collectionResourceRel="cars", path="cars")
interface CarRepository extends JpaRepository<Car, Long> {
	
	@RestResource(rel="make", path="make")
	Page<Car> findByMake(@Param("make") String make, Pageable pageable)
}
