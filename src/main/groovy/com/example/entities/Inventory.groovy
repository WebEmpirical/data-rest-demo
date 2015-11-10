package com.example.entities

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Index
import javax.persistence.Table

@Entity
@Table(name="inventory", indexes=[ @Index(columnList="category", unique=false) ])
class Inventory implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	long id
	
	@Column
	long category
	
	@Column
	String item
	
	@Column
	String description
	
	@Column
	long price
	
	@Column
	long onHand
}
