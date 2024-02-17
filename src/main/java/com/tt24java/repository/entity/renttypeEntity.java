package com.tt24java.repository.entity;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="renttype")
public class renttypeEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="code")
	private String code;
	

	
	@Column(name="name")
	private String name;
	
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(name="buildingrenttype",
			joinColumns=@JoinColumn(name="renttypeid",nullable=false),
			inverseJoinColumns=@JoinColumn(name="buildingid",nullable=false))
	private List<BuildingEntity>buildings=new ArrayList<>();

}
