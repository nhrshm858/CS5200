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

	public class MovieManager {
		
		DataSource ds;
		String createMovieSql = "INSERT INTO MOVIE (id, movieId, title, posterImage, releaseDate) VALUES (NULL, ?, ?, ?, ?)";
		String readAllMoviesSql = "SELECT * FROM MOVIE";
		String readMovieSql = "SELECT * FROM MOVIE WHERE id=?";
		String updateMovieSql = "UPDATE MOVIE SET movieId=?, title=?, posterImage=?, releaseDate=? WHERE id=?";
		String deleteMovieSql = "DELETE FROM MOVIE WHERE id=?";
		PreparedStatement stmt;
		ResultSet result;
		ResultSet results;
		
		public MovieManager()
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
		
		
		
		// create a movie
		public void createMovie(Movie movie)
		{
			
			try {
				Connection connection = ds.getConnection();
				stmt = connection.prepareStatement(createMovieSql);
				stmt.setString(1, movie.getMovieId());
				stmt.setString(2, movie.getTitle());
				stmt.setString(3, movie.getPosterImage());
				stmt.setDate(4, movie.getReleaseDate());
				stmt.execute();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return null;
		}
		
		
		
		// retrieve a list of all movies
		public List<Movie> readAllMovies()
		{
			List<Movie> movies = new ArrayList<Movie>();
	
			
			try {
				Connection connection = ds.getConnection();
				stmt = connection.prepareStatement(readAllMoviesSql);
				results = stmt.executeQuery();
				while(results.next())
				{
					Movie movie = new Movie();
			        movie.setId(results.getInt("id"));
					movie.setMovieId(results.getString("movieId"));
					movie.setTitle(results.getString("title"));
					movie.setPosterImage(results.getString("posterImage"));
					movie.setReleaseDate(results.getDate("releaseDate"));
					movies.add(movie);
				
				}
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return movies;
			
		}
		
		
		
		// retrieve a movie by id
		public Movie readMovie(int id)
		{
			Movie movie = new Movie();
				
			
			try {
				Connection connection = ds.getConnection();
				stmt = connection.prepareStatement(readMovieSql);
				stmt.setInt(1,id);
				result = stmt.executeQuery();
				if (result.next())
				{
					movie.setId(result.getInt("id"));
					movie.setMovieId(result.getString("movieId"));
					movie.setTitle(result.getString("title"));
					movie.setPosterImage(result.getString("posterImage"));
					movie.setReleaseDate(result.getDate("releaseDate"));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return movie;
			
			}
		
		
		// update a movie by id
		public Movie updateMovie(int id, Movie movie)
		{

			try {
				Connection connection = ds.getConnection();
				stmt = connection.prepareStatement(updateMovieSql);
				stmt.setString(1, movie.getMovieId());
				stmt.setString(2, movie.getTitle());
				stmt.setString(3, movie.getPosterImage());
				stmt.setDate(4, movie.getReleaseDate());
				stmt.setInt(5, id);
				stmt.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return movie;
		
		}
		
		
		// delete movie by id
		public int deleteMovie(int id)
		{

			try {
				Connection connection = ds.getConnection();
				stmt = connection.prepareStatement(deleteMovieSql);
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
			MovieManager mm = new MovieManager();
			Movie Movie1 = new Movie();
			
			java.sql.Date date = java.sql.Date.valueOf("2013-09-04");
			Movie Avatar2 = new Movie("abc", "Avatar2", "www.google.com/Avatar2", date);
			
			
			int id = 1;
			Movie1 = mm.readMovie(id);
			System.out.println("Id: " + Movie1.getId());
			System.out.println("MovieId: " + Movie1.getMovieId());
			System.out.println("Title: " + Movie11.getTitle());
			System.out.println("PosterImage: " + Movie1.getPosterImage());
			System.out.println("ReleaseDate: " + Movie1.getReleaseDate());
			
			
			mm.updateMovie(3, Avatar2);
			
			mm.deleteMovie(3);
			
			
			List<Movie> movies = mm.readAllMovies();
			for(Movie movie: movies)
			{
				System.out.println(movie.getTitle());
			}
			
//			mm.createMovie(Avatar2);
		}
		
	}

