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


	List<Object> list(String name) {
		def qry = "select new map(i.id as id, i.item as item, c.name as category) from Category c right join c.inventory i where upper(c.name) like upper(:name+'%')"
		List<Object> results = em.createQuery(qry).setParameter('name',name).getResultList()
		return results
	}

}
