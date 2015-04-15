package edu.neu.CS5200;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.sql.DataSource;

	public class ActorManager {
		
		DataSource ds;
		String createActorSql = "INSERT INTO ACTOR (id, actorId, firstName, lastName, dateOfBirth) VALUES (NULL, ?, ?, ?, ?)";
		String readAllActorsSql = "SELECT * FROM ACTOR";
		String readActorSql = "SELECT * FROM ACTOR WHERE id=?";
		String updateActorSql = "UPDATE ACTOR SET actorId=?, firstName=?, lastName=?, dateOfBirth=? WHERE id=?";
		String deleteActorSql = "DELETE FROM ACTOR WHERE id=?";
		PreparedStatement stmt;
		ResultSet result;
		ResultSet results;
		
		
		public ActorManager()
		{
			try {
			    Context ctx = new InitialContext();
			    ds = (DataSource)ctx.lookup("java:comp/env/jdbc/jdbcHW");
			    System.Out.Println(ds);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
			
		}
		
		
		// create an actor
				public void createActor(Actor actor)
				{
					
					try {
						Connection connection = ds.getConnection();
						stmt = connection.prepareStatement(createActorSql);
						stmt.setString(1, actor.getActorId());
						stmt.setString(2, actor.getFirstName());
						stmt.setString(3, actor.getLastName());
						stmt.setDate(4, actor.getDateOfBirth());
						stmt.execute();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					return null;
				}
				
				
				
				// retrieve a list of all actors
				public List<Actor> readAllActors()
				{
					List<Actor> actors = new ArrayList<Actor>();
			
					
					try {
						Connection connection = ds.getConnection();
						stmt = connection.prepareStatement(readAllActorsSql);
						results = stmt.executeQuery();
						while(results.next())
						{
							Actor actor = new Actor();
					        actor.setId(results.getInt("id"));
							actor.setActorId(results.getString("actorId"));
							actor.setFirstName(results.getString("firstName"));
							actor.setLastName(results.getString("lastName"));
							actor.setDateOfBirth(results.getDate("dateOfBirth"));
							actors.add(actor);
						
						}
						
						
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					return actors;
					
				}
				
				
				
				// retrieve an actor by id
				public Actor readActor(int id)
				{
					Actor actor = new Actor();
						
					
					try {
						Connection connection = ds.getConnection();
						stmt = connection.prepareStatement(readActorSql);
						stmt.setInt(1,id);
						result = stmt.executeQuery();
						if (result.next())
						{
							actor.setId(results.getInt("id"));
							actor.setActorId(results.getString("actorId"));
							actor.setFirstName(results.getString("firstName"));
							actor.setLastName(results.getString("lastName"));
							actor.setDateOfBirth(results.getDate("dateOfBirth"));
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					return actor;
					
					}
				
				
				// update a actor by id
				public Actor updateActor(int id, Actor actor)
				{

					try {
						Connection connection = ds.getConnection();
						stmt = connection.prepareStatement(updateActorSql);
						stmt.setString(1, actor.getActorId());
						stmt.setString(2, actor.getFirstName());
						stmt.setString(3, actor.getLastName());
						stmt.setDate(4, actor.getDateOfBirth());
						stmt.setInt(5, id);
						stmt.executeUpdate();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					return actor;
				
				}
				
				
				// delete actor by id
				public int deleteActor(int id)
				{

					try {
						Connection connection = ds.getConnection();
						stmt = connection.prepareStatement(deleteActorSql);
						stmt.setInt(1, id);
						return stmt.executeUpdate();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					return 0;

				}
				

				
				public static void main(String[] args)
				{
					ActorManager am = new ActorManager();
					Actor Actor1 = new Actor();
					
					java.sql.Date date = java.sql.Date.valueOf("2013-09-04");
					Actor Actor12 = new Actor("actor12", "john", "walker", date);
					
					int id = 1;
					Actor1 = am.readActor(id);
					System.out.println("Id: " + User1.getId());
					System.out.println("ActorId: " + Actor1.getActorId());
					System.out.println("FirstName: " + Actor1.getFirstName());
					System.out.println("LastName: " + Actor1.getLastName());
					System.out.println("DateOfBirth: " + Actor1.getDateOfBirth());
					
					
					am.updateActor(3, Actor12);
					am.deleteActor(3);
					
					List<Actor> actors = am.readAllActors();
					for(Actor actor: actors)
					{
						System.out.println(actor.getActorId());
					}
					
//					am.createActor(Actor12);
				}
				
			}


		
	
		
		
	