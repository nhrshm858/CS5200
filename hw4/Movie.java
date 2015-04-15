package edu.neu.CS5200;

import java.util.Date;

public class Movie {
	private int id;
	private String movieId;
	private String title;
	private String posterImage;
	private Date releaseDate;
	
	public Movie() {
		super();
	}
	
	public Movie(int id, String movieId, String title, String posterImage,
			Date releaseDate) {
		super();
		this.id = id;
		this.movieId = movieId;
		this.title = title;
		this.posterImage = posterImage;
		this.releaseDate = releaseDate;
	}

    public Movie(String movieId, String title, String posterImage, Date releaseDate) {
		super();
		this.movieId = movieId;
		this.title = title;
		this.posterImage = posterImage;
		this.releaseDate = releaseDate;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	
	public String getMovieId() {
		return movieId;
	}
	
	public void setMovieId(String movieId) {
		this.movieId = movieId;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getPosterImage() {
		return posterImage;
	}
	
	public void setPosterImage(String posterImage) {
		this.posterImage = posterImage;
	}
	
	public Date getReleaseDate() {
		return releaseDate;
	}
	
	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	
}
