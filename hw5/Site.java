package edu.neu.cs5200.JPA.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

@Entity
@NamedQuery(
		name = "findAllSites",
		query = "select site from Site site")

public class Site {
	
	@Id
	private  int id;
	private String name;
	private double latitude;
	private double longitude;
	@OneToMany(mappedBy="site", cascade=CascadeType.ALL, orphanRemoval=true)
	private List<Tower> towers;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public double getLatitude() {
		return latitude;
	}
	
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	
	public double getLongitude() {
		return longitude;
	}
	
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	
	public List<Tower> getTowers() {
		return towers;
	}
	
	public void setTowers(List<Tower> towers) {
		this.towers = towers;
	}

	public Site(int id, String name, double latitude, double longitude,
			List<Tower> towers) {
		super();
		this.id = id;
		this.name = name;
		this.latitude = latitude;
		this.longitude = longitude;
		this.towers = towers;
	}

	public Site() {
		super();
	}
	
}
