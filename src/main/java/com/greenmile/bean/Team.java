package com.greenmile.bean;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Team {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	Long id;
	
	String name;
	
	@OneToMany(cascade=CascadeType.ALL)
	List<Member> members;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setNome(String name) {
		this.name = name;
	}

	public List<Member> getMembers() {
		return members;
	}

	public void setMembros(List<Member> members) {
		this.members = members;
	}

	
	
	
}
