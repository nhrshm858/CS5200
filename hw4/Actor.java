package edu.neu.CS5200;

import java.util.Date;

public class Actor {
	private int id;
	private String actorId;
	private String firstName;
	private String lastName;
	private Date dateOfBirth;
	
	public Actor() {
		super();
	}
	
	
	public Actor(int id, String actorId, String firstName, String lastName,
			Date dateOfBirth) {
		super();
		this.id = id;
		this.actorId = actorId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfBirth = dateOfBirth;
	}
	
	
	public Actor(String actorId, String firstName, String lastName,
			Date dateOfBirth) {
		super();
		this.actorId = actorId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfBirth = dateOfBirth;
	}

    
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getActorId() {
		return actorId;
	}

	public void setActorId(String ActorId) {
		this.actorId = ActorId;
	}

	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

}
