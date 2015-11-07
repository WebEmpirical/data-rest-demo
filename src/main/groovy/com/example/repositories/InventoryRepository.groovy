package com.example.repositories

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource

import com.example.entities.Inventory;;

@RepositoryRestResource(collectionResourceRel="inventory", path="inventory")
interface InventoryRepository extends JpaRepository<Inventory, Long> {
	
}
