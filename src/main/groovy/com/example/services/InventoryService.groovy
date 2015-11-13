package com.example.services

import javax.persistence.EntityManager

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service

import com.example.dto.InventoryDetail

@Service
class InventoryService {
	
	// the EntityManager will be used to execute custom queries
	// we want to use hql/jpql as much as possible to keep the sql portable to whatever platform
	EntityManager em
	
	@Autowired
	InventoryService(EntityManager em) {
		super()
		this.em = em
	}

	
	Object inventoryByCategoryName(String name,int page,int size,String sort) {
		// our columns will be populated into the InventoryDetail DTO
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
		
		// we tokenize the sort values into an ArrayList, splitting/tokenizing them on the commas
		// it should look like fieldA[,asc or desc],fieldB[,asc or desc]
		def token = sort.tokenize(',')
		
		// check the size to make sure we really want to sort
		if(token.size() > 1) {
			// start appending the query with the order by clause
			qry += "order by "
			// loop with index
			token.eachWithIndex { it, i ->
				// if this is an even one, let's append the string with the field and sort direction followed by a comma
				if(i%2==0){
					qry += it + ' ' + token[i+1] + ','
				}
			}
			// strip the comma at the very end of the string
			qry = qry.substring(0, qry.length() -1)
		}
		// we need a count query to determine the number of records
		def cnt = """
					select count(i)
					from Category c
					join c.inventory i
					where upper(c.name) like upper(:name+'%')
		"""
		// define the actual page number here dynamically so that we have flexibility
		// page numbers are 0 based indexes
		int number = (page-1)*size
		// this is the totalRecords from our cnt query
		Long totalRecords = em.createQuery(cnt).setParameter('name',name).getSingleResult()
		// get the results and pass in our parameter(s) along with the page number and result size
		List<InventoryDetail> results = em.createQuery(qry).setParameter('name',name).setFirstResult(number).setMaxResults(size).getResultList()
		// return a Pageable object
		return new PageImpl<InventoryDetail>(results,new PageRequest(page-1,size),totalRecords)
	}

}
