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

	public class CommentManager {
		
		DataSource ds;
		String createCommentSql = "INSERT INTO COMMENT (commentId, userId, movieId, comment, date) VALUES (NULL, ?, ?, ?, ?)";
		String readAllCommentsSql = "SELECT * FROM COMMENT";
		String readAllCommentsForUserSql = "SELECT * FROM COMMENT WHERE userId=?";
		String readAllCommentsForMovieSql = "SELECT * FROM COMMENT WHERE movieId=?";
		String readCommentSql = "SELECT * FROM COMMENT WHERE commentId=?";
		String updateCommentSql = "UPDATE COMMENT SET userId=?, movieId=?, comment=?, date=?  WHERE commentId=?";
		String deleteCommentSql = "DELETE FROM USER WHERE commentId=?";
		PreparedStatement stmt;
		ResultSet result;
		ResultSet results;
		
		
		public CommentManager()
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
		
		
		// create a comment
				public void createComment(Comment comment)
				{
					
					try {
						Connection connection = ds.getConnection();
						stmt = connection.prepareStatement(createCommentSql);
						stmt.setInt(1, comment.getCommentId());
						stmt.setInt(2, comment.getUserId());
						stmt.setInt(3, comment.getMovieId());
						stmt.setString(4, comment.getComment());
						stmt.setDate(5, comment.getDate());
						stmt.execute();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					return null;
				}
				
				
				
				// retrieve a list of all comments
				public List<Comment> readAllComments()
				{
					List<Comment> comments = new ArrayList<Comment>();
			
					
					try {
						Connection connection = ds.getConnection();
						stmt = connection.prepareStatement(readAllCommentsSql);
						results = stmt.executeQuery();
						while(results.next())
						{
							Comment comment = new Comment();
					        comment.setCommentId(results.getInt("commentId"));
							comment.setUserId(results.getInt("userId"));
							comment.setMovieId(results.getInt("movieId"));
							comment.setComment(results.getString("comment"));
							comment.setDate(results.getDate("date"));
							comments.add(comment);
						
						}
						
						
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					return comments;
					
				}
				
				
				// retrieve a list of all comments for a given User
				public List<Comment> readAllCommentsForUser(int userId)
				{
					List<Comment> comments = new ArrayList<Comment>();
			
					
					try {
						Connection connection = ds.getConnection();
						stmt = connection.prepareStatement(readAllCommentsForUserSql);
						stmt.setInt(1,userId);
						results = stmt.executeQuery();
						while(results.next())
						{
							Comment comment = new Comment();
							 comment.setCommentId(results.getInt("commentId"));
						     comment.setUserId(results.getInt("userId"));
							 comment.setMovieId(results.getInt("movieId"));
							 comment.setComment(results.getString("comment"));
							 comment.setDate(results.getDate("date"));
							 comments.add(comment);
						
						}
						
						
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					return comments;
					
				}
				
				
				// retrieve a list of all comments for a given Movie
				public List<Comment> readAllCommentsForUser(int movieId)
				{
					List<Comment> comments = new ArrayList<Comment>();
			
					
					try {
						Connection connection = ds.getConnection();
						stmt = connection.prepareStatement(readAllCommentsForMovieSql);
						stmt.setInt(1,movieId);
						results = stmt.executeQuery();
						while(results.next())
						{
							Comment comment = new Comment();
							 comment.setCommentId(results.getInt("commentId"));
						     comment.setUserId(results.getInt("userId"));
							 comment.setMovieId(results.getInt("movieId"));
							 comment.setComment(results.getString("comment"));
							 comment.setDate(results.getDate("date"));
							 comments.add(comment);
						
						}
						
						
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					return comments;
					
				}
				
				
				
				// retrieve a comment by commentId
				public Comment readCommentForId(int commentId)
				{
					Comment comment = new Comment();
						
					
					try {
						Connection connection = ds.getConnection();
						stmt = connection.prepareStatement(readCommentSql);
						stmt.setInt(1,commentId);
						result = stmt.executeQuery();
						if (result.next())
						{
							comment.setCommentId(results.getInt("commentId"));
							comment.setUserId(results.getInt("userId"));
							omment.setMovieId(results.getInt("movieId"));
							comment.setComment(results.getString("comment"));
							comment.setDate(results.getDate("date"));
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					return comment;
					
					}
				
				
				// update a comment by commentId
				public Comment updateComment(int commentId, Comment comment)
				{

					try {
						Connection connection = ds.getConnection();
						stmt = connection.prepareStatement(updateCommentSql);
						stmt.setInt(1, comment.getUserId());
						stmt.setInt(2, comment.getMovieId());
						stmt.setString(3, comment.getComment());
						stmt.setDate(4, comment.getDate());
						stmt.setInt(5, commentId);
						stmt.executeUpdate();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					return comment;
				
				}
				
				
				// delete comment by commentId
				public int deleteComment(int commentId)
				{

					try {
						Connection connection = ds.getConnection();
						stmt = connection.prepareStatement(deleteCommentSql);
						stmt.setInt(1, commentId);
						return stmt.executeUpdate();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					return 0;

				}
				

				
				public static void main(String[] args)
				{
					CommentManager cm = new CommentManager();
					Comment Comment1 = new Comment();
					
					java.sql.Date date = java.sql.Date.valueOf("2013-09-04");
					Comment Comment12 = new Comment(1, 23, 32, "walker is best", date);
					
					int commentid = 1;
					Comment1 = cm.readComment(commentid);
					System.out.println("CommentId: " + Comment1.getCommentId());
					System.out.println("UserId: " + Comment1.getUserId());
					System.out.println("MovieId: " + Comment1.getMovieId());
					System.out.println("Comment: " + Comment1.getComment());
					System.out.println("Date: " + Comment1.getDate());
					
					
					cm.updateComment(3, Comment12);
					
					cm.deleteComment(3);
					
					List<Comment> comments = cm.readAllComments();
					for(Comment comment: comments)
					{
						System.out.println(comment.getComment());
					}
					
//					cm.createComment(Comment12);
				}
				

		}

		
	
		
		

