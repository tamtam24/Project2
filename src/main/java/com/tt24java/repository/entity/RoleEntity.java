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
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="role")
public class RoleEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="name",nullable=false)
	private String name;
	
	@Column(name="code",unique=true,nullable=false)
	private String code;
	
	@Column(name="createdate")
	private Date createdate;
	
	@Column(name="modifieddate")
	private Date modifieddate;
	
	@Column(name="createdby")
	private String createdby;
	
	@Column(name="modifiedby")
	private String modifiedby;
	
//	@OneToMany(mappedBy="role",fetch = FetchType.LAZY)
//	private List<UserRoleEntity>userRoleEntities = new ArrayList<>();

	@ManyToMany(mappedBy="roles",fetch=FetchType.LAZY)	
	private List<UserEntity>users=new ArrayList<>();
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Date getCreatedate() {
		return createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

	public Date getModifieddate() {
		return modifieddate;
	}

	public void setModifieddate(Date modifieddate) {
		this.modifieddate = modifieddate;
	}

	public String getCreatedby() {
		return createdby;
	}

	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}

	public String getModifiedby() {
		return modifiedby;
	}

	public void setModifiedby(String modifiedby) {
		this.modifiedby = modifiedby;
	}

	public List<UserEntity> getUsers() {
		return users;
	}

	public void setUsers(List<UserEntity> users) {
		this.users = users;
	}

	
	
//	public List<UserRoleEntity> getUserRoleEntities() {
//		return userRoleEntities;
//	}
//
//	public void setUserRoleEntities(List<UserRoleEntity> userRoleEntities) {
//		this.userRoleEntities = userRoleEntities;
//	}
	
	
	
	

}