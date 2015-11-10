package com.example.services

import javax.persistence.EntityManager

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

import com.example.dto.InventoryDetail

@Service
class InventoryService {
	
	EntityManager em
	
	@Autowired
	InventoryService(EntityManager em) {
		super()
		this.em = em
	}


	List<Object> inventoryByCategoryName(String name,int page,int size) {
		def qry = """
					select
					new com.example.dto.InventoryDetail(
						i.id,
						c.id,
						c.name,
						i.item,
						i.description,
						i.price,
						i.onHand
					)
					from Category c
					join c.inventory i
					where upper(c.name) like upper(:name+'%')
		"""
		List<InventoryDetail> results = em.createQuery(qry).setParameter('name',name).setFirstResult((page-1)*size).setMaxResults(size).getResultList()
		return results
	}

}
