package edu.neu.cs5200.JPA.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Tower {
	
	@Id
	private int id;
	private String name;
	private double height;
	private int sides;
	@OneToMany(mappedBy="tower", cascade=CascadeType.ALL, orphanRemoval=true)
	private List<Equipment> equipments;
	@ManyToOne
	@JoinColumn(name="siteId")
	private Site site;
	
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
	
	public double getHeight() {
		return height;
	}
	
	public void setHeight(double height) {
		this.height = height;
	}
	
	public int getSides() {
		return sides;
	}
	
	public void setSides(int sides) {
		this.sides = sides;
	}
	
	public List<Equipment> getEquipments() {
		return equipments;
	}
	
	public void setEquipments(List<Equipment> equipments) {
		this.equipments = equipments;
	}
	
	public Site getSite() {
		return site;
	}
	
	public void setSite(Site site) {
		this.site = site;
	}

	public Tower(int id, String name, double height, int sides,
			List<Equipment> equipments, Site site) {
		super();
		this.id = id;
		this.name = name;
		this.height = height;
		this.sides = sides;
		this.equipments = equipments;
		this.site = site;
	}

	public Tower() {
		super();
	}
	
}
