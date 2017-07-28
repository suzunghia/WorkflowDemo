package com.workflow.demo.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.Getter;

@Data
@Entity
public class Member {
	public Member(){}
	
	public Member(String name) {
		super();
		this.name = name;
	}
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
	public long getId() {
		return id;
	}
	
	@Column(nullable = false, length = 30)
	private String name;
	public String getName() {
		return name;
	}
}
