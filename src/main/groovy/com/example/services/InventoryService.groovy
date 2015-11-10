package com.example.services

import javax.persistence.EntityManager

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class InventoryService {
	
	EntityManager em
	
	@Autowired
	InventoryService(EntityManager em) {
		super()
		this.em = em
	}


	List<Object> inventoryByCategoryName(String name,int page,int size) {
		def qry = "select new map(i.id as id, i.item as item, c.name as category) from Category c join c.inventory i where upper(c.name) like upper(:name+'%')"
		List<Object> results = em.createQuery(qry).setParameter('name',name).setFirstResult((page-1)*size).setMaxResults(size).getResultList()
		return results
	}

}
