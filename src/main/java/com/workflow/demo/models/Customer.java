package com.workflow.demo.models;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Customer {
	public Customer(){}
	
	public Customer(String firstName, String lastName, Date dateOfBirth) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfBirth = dateOfBirth;
	}
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
//	public long getId()
//	{
//		return id;
//	}
	
	@Column(nullable = false, length = 30)
	private String firstName;
//	public String getFirstName()
//	{
//		return firstName;
//	}
	

	@Column(nullable = false, length = 30)
	private String lastName;
//	public String getLastName()
//	{
//		return lastName;
//	}
	

	@Column(nullable = false)
	private Date dateOfBirth;
//	public Date getDateOfBirth()
//	{
//		return dateOfBirth;
//	}
}
