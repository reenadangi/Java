package com.events.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name="locations")
public class Location {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Size(min = 2, max = 200)
    private String location;
	@OneToMany(mappedBy="location",cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	private List<User> users;
	public Location() {
		
	}
	public Long getId() {
		return id;
	}
	public String getLocation() {
		return location;
	}
	public List<User> getUsers() {
		return users;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}
	
}
