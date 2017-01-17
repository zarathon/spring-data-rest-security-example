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
public class Team {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	Long id;
		
	@ManyToMany(fetch=FetchType.LAZY)
	List<Member> members;

	@Column(nullable=false, unique=true)
	String name;
	
	public Team() {
	}
	
	public Team(String name) {
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
