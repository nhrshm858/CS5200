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

	public class CastManager {
		
		DataSource ds;
		String createCastSql = "INSERT INTO CAST (castId, actorId, movieId, characterName) VALUES (NULL, ?, ?, ?, ?)";
		String readAllCastsSql = "SELECT * FROM CAST";
		String readAllCastsForActorSql = "SELECT * FROM CAST WHERE actorId=?";
		String readAllCastsForMovieSql = "SELECT * FROM CAST WHERE movieId=?";
		String readCastSql = "SELECT * FROM CAST WHERE castId=?";
		String updateCastSql = "UPDATE CAST SET actorId=?, movieId=?, characterName=?, WHERE castId=?";
		String deleteCastSql = "DELETE FROM CAST WHERE castId=?";
		PreparedStatement stmt;
		ResultSet result;
		ResultSet results;
		
		
		public CastManager()
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
		
		
		// create a cast
		public void createCast(Cast cast)
		{
			
			try {
				Connection connection = ds.getConnection();
				stmt = connection.prepareStatement(createCastSql);
				stmt.setInt(1, cast.getCastId());
				stmt.setInt(2, cast.getActorId());
				stmt.setInt(3, cast.getMovieId());
				stmt.setString(4, cast.getCharacterName());
				stmt.execute();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return null;
		}
				
				
		
		// retrieve a list of all casts
		public List<Cast> readAllCasts()
		{
			List<Cast> casts = new ArrayList<Cast>();
	
			
			try {
				Connection connection = ds.getConnection();
				stmt = connection.prepareStatement(readAllCastsSql);
				results = stmt.executeQuery();
				while(results.next())
				{
					Cast cast = new Cast();
			        cast.setCastId(results.getInt("castId"));
					cast.setActorId(results.getInt("actorId"));
					cast.setMovieId(results.getInt("movieId"));
					cast.setCharacterName(results.getString("characterName"));
					casts.add(cast);
				
				}
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return comments;
			
		}
		
		
		// retrieve a list of all comments for a given Actor
		public List<Cast> readAllCastsForActor(int actorId)
		{
			List<Cast> casts = new ArrayList<Cast>();
	
			
			try {
				Connection connection = ds.getConnection();
				stmt = connection.prepareStatement(readAllCastsForActorSql);
				stmt.setInt(1,actorId);
				results = stmt.executeQuery();
				while(results.next())
				{
					Cast cast = new Cast();
					 cast.setCastId(results.getInt("castId"));
				     cast.setActorId(results.getInt("actorId"));
					 cast.setMovieId(results.getInt("movieId"));
					 cast.setCharacterName(results.getString("characterName"));
					 casts.add(cast);
				
				}
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return casts;
			
		}
		
		
		// retrieve a list of all casts for a given Movie
		public List<Cast> readAllCastsForMovie(int movieId)
		{
			List<Cast> casts = new ArrayList<Cast>();
	
			
			try {
				Connection connection = ds.getConnection();
				stmt = connection.prepareStatement(readAllCastsForActorSql);
				stmt.setInt(1,movieId);
				results = stmt.executeQuery();
				while(results.next())
				{
					Cast cast = new Cast();
					 cast.setCastId(results.getInt("castId"));
				     cast.setActorId(results.getInt("actorId"));
					 cast.setMovieId(results.getInt("movieId"));
					 cast.setCharacterName(results.getString("characterName"));
					 casts.add(cast);
				
				}
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return casts;
			
		}
		
		
		
		
		// retrieve a cast by castId
		public Cast readCastForId(int castId)
		{
			Cast cast = new Cast();
				
			
			try {
				Connection connection = ds.getConnection();
				stmt = connection.prepareStatement(readCastSql);
				stmt.setInt(1,castId);
				result = stmt.executeQuery();
				if (result.next())
				{
					cast.setCastId(results.getInt("castId"));
					cast.setActorId(results.getInt("actorId"));
					cast.setMovieId(results.getInt("movieId"));
					cast.setCharacterName(results.getString("characterName"));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return cast;
			
			}
		
		
		
		// update a cast by castId
		public Cast updateCast(int castId, Cast cast)
		{

			try {
				Connection connection = ds.getConnection();
				stmt = connection.prepareStatement(updateCastSql);
				stmt.setInt(1, cast.getActorId());
				stmt.setInt(2, cast.getMovieId());
				stmt.setString(3, cast.getCHaracterName());
				stmt.setInt(5, castId);
				stmt.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return cast;
		
		}
		
		
		// delete cast by castId
		public int deleteCast(int castId)
		{

			try {
				Connection connection = ds.getConnection();
				stmt = connection.prepareStatement(deleteCastSql);
				stmt.setInt(1, castId);
				return stmt.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return 0;

		}
		

		
		public static void main(String[] args)
		{
			CastManager cam = new CastManager();
			Cast Cast1 = new Cast();
			
			Cast Cast12 = new Cast(1, 23, 32, "walker");
			
			int castid = 1;
			Comment1 = cm.readComment(castid);
			System.out.println("CastId: " + Cast1.getCommentId());
			System.out.println("ActorId: " + Cast1.getActorId());
			System.out.println("MovieId: " + Cast1.getMovieId());
			System.out.println("CharacterName: " + Cast1.getCharacterName());
			
			
			cam.updateCast(3, Cast12);
			
			cam.deleteCast(3);
			
			List<Cast> casts = cam.readAllCasts();
			for(Cast cast: casts)
			{
				System.out.println(cast.getCharacterName());
			}
			
//			cam.createCast(Cast12);
		}
		

}






		