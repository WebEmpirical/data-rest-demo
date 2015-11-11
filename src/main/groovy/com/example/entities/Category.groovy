package com.example.entities

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.OneToMany
import javax.persistence.Table

@Entity
@Table(name="categories")
class Category implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	long id
	
	@Column
	String name
	
	@Column
	String description
	
	// defines the relationship with Inventory and identifies the join column in the related entity
	// returns a List of Inventory objects
	@OneToMany(targetEntity=Inventory.class, fetch=FetchType.LAZY)
	@JoinColumn(name="category")
	List<Inventory> inventory
	
}
