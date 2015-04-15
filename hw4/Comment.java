package edu.neu.CS5200;

import java.util.Date;

public class Comment {
	private int commentId;
	private int userId;
	private int movieId;
	private String comment;
	private Date date;
	
	
	public Comment() {
		super();
	}

	
	public Comment(int commentId, int userId, int movieId,
			String comment, Date date) {
		super();
		this.commentId = commentId;
		this.userId = userId;
		this.movieId = movieId;
		this.comment = comment;
		this.date = date;
	}


	
	public int getCommentId() {
		return commentId;
	}

	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}
	
	public int getUserId() {
		return userId;
	}
	
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	public int getMovieId() {
		return movieId;
	}
	
	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}
	
	public String getComment() {
		return comment;
	}
	
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	public Date getDate() {
		return date;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}

	
}
