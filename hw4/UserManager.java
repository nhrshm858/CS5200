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

	public class UserManager {
		
		DataSource ds;
		String createUserSql = "INSERT INTO USER (id, username, password, firstName, lastName, email, dateOfBirth) VALUES (NULL, ?, ?, ?, ?, ?, ?)";
		String readAllUsersSql = "SELECT * FROM USER";
		String readUserSql = "SELECT * FROM USER WHERE id=?";
		String updateUserSql = "UPDATE USER SET username=?, password=?, firstName=?, lastName=?, email=?, dateOfBirth=? WHERE id=?";
		String deleteUserSql = "DELETE FROM USER WHERE id=?";
		PreparedStatement stmt;
		ResultSet result;
		ResultSet results;
		
		
		public UserManager()
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
		
		
		// create a user
				public void createUser(User user)
				{
					
					try {
						Connection connection = ds.getConnection();
						stmt = connection.prepareStatement(createUserSql);
						stmt.setString(1, user.getUserName());
						stmt.setString(2, user.getPassword());
						stmt.setString(3, user.getFirstName());
						stmt.setString(4, user.getLastName());
						stmt.setString(5, user.getEmail());
						stmt.setDate(6, user.getDateOfBirth());
						stmt.execute();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					return null;
				}
				
				
				
				// retrieve a list of all users
				public List<User> readAllUsers()
				{
					List<User> users = new ArrayList<User>();
			
					
					try {
						Connection connection = ds.getConnection();
						stmt = connection.prepareStatement(readAllUsersSql);
						results = stmt.executeQuery();
						while(results.next())
						{
							User user = new User();
					        user.setId(results.getInt("id"));
							user.setUsername(results.getString("username"));
							user.setPassword(results.getString("password"));
							user.setFirstName(results.getString("firstName"));
							user.setLastName(results.getString("lastName"));
							user.setEmail(results.getString("email"));
							user.setDateOfBirth(results.getDate("dateOfBirth"));
							users.add(user);
						
						}
						
						
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					return users;
					
				}
				
				
				
				// retrieve a user by id
				public User readUser(int id)
				{
					User user = new User();
						
					
					try {
						Connection connection = ds.getConnection();
						stmt = connection.prepareStatement(readUserSql);
						stmt.setInt(1,id);
						result = stmt.executeQuery();
						if (result.next())
						{
							user.setId(results.getInt("id"));
							user.setUsername(results.getString("username"));
							user.setPassword(results.getString("password"));
							user.setFirstName(results.getString("firstName"));
							user.setLastName(results.getString("lastName"));
							user.setEmail(results.getString("email"));
							user.setDateOfBirth(results.getDate("dateOfBirth"));
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					return user;
					
					}
				
				
				// update a user by id
				public User updateUser(int id, User user)
				{

					try {
						Connection connection = ds.getConnection();
						stmt = connection.prepareStatement(updateUserSql);
						stmt.setString(1, user.getUsername());
						stmt.setString(2, user.getPassword());
						stmt.setString(3, user.getFirstName());
						stmt.setString(4, user.getLastName());
						stmt.setString(5, user.getEmail());
						stmt.setDate(6, user.getDateOfBirth());
						stmt.setInt(7, id);
						stmt.executeUpdate();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					return user;
				
				}
				
				
				// delete user by id
				public int deleteUser(int id)
				{

					try {
						Connection connection = ds.getConnection();
						stmt = connection.prepareStatement(deleteUserSql);
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
					UserManager um = new UserManager();
					User User1 = new User();
					
					java.sql.Date date = java.sql.Date.valueOf("2013-09-04");
					User User12 = new User("user12", "abc123", "john", "walker", "user12@yahoo.com", date);
					
					int id = 1;
					User1 = um.readUser(id);
					System.out.println("Id: " + User1.getId());
					System.out.println("UserName: " + User1.getUsername());
					System.out.println("Password: " + User1.getPassword());
					System.out.println("FirstName: " + User1.getFirstName());
					System.out.println("LastName: " + User1.getLastName());
					System.out.println("Email: " + User1.getEmail());
					System.out.println("DateOfBirth: " + User1.getDateOfBirth());
					
					
					um.updateUser(3, User12);
					
					um.deleteUser(3);
					
					List<User> users = um.readAllUsers();
					for(User user: users)
					{
						System.out.println(user.getUsername());
					}
					
//					um.createUser(User12);
				}
				

		}

		
	
		
		
	