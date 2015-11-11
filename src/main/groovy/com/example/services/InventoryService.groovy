package com.example.services

import javax.persistence.EntityManager

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

import com.example.dto.InventoryDetail
import com.example.dto.Page

@Service
class InventoryService {
	
	EntityManager em
	
	@Autowired
	InventoryService(EntityManager em) {
		super()
		this.em = em
	}


	Object inventoryByCategoryName(String name,int page,int size,String sort) {
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
		
		def token = sort.tokenize(',')

		if(token.size() > 1) {
			qry += "order by "
			token.eachWithIndex { it, i ->
				if(i%2==0){
					qry += it + ' ' + token[i+1] + ','
				}
			}
		}

		qry = qry.substring(0, qry.length() -1)
		
		def cnt = """
					select count(i)
					from Category c
					join c.inventory i
					where upper(c.name) like upper(:name+'%')
		"""
		int number = (page-1)*size
		Long totalRecords = em.createQuery(cnt).setParameter('name',name).getSingleResult()
		List<InventoryDetail> results = em.createQuery(qry).setParameter('name',name).setFirstResult(number).setMaxResults(size).getResultList()
		return [results,new Page(size,totalRecords,number)]
	}

}
