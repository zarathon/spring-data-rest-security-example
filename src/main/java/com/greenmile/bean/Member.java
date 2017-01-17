package com.greenmile.bean;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Member {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	Long id;
	
	@ManyToMany(mappedBy="members", fetch=FetchType.LAZY)
	List<Team> teams;
	
	@Column(nullable=false, unique=true)
	String name;
	
	public Member() {
	}
	
	public Member(String name) {
		this.name = name;
	}

	
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

	
	
}
