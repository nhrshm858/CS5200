package edu.neu.CS5200;


public class Cast {
	private int castId;
	private int actorId;
	private int movieId;
	private String characterName;
	
	public Cast() {
		super();
	}

	public Cast(int castId, int actorId, int movieId, String characterName) {
		super();
		this.setCastId(castId);
		this.setActorId(actorId);
		this.setMovieId(movieId);
		this.setCharacterName(characterName);
	}

	public int getCastId() {
		return castId;
	}

	public void setCastId(int castId) {
		this.castId = castId;
	}

	public int getActorId() {
		return actorId;
	}

	public void setActorId(int actorId) {
		this.actorId = actorId;
	}

	public int getMovieId() {
		return movieId;
	}

	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}

	public String getCharacterName() {
		return characterName;
	}

	public void setCharacterName(String characterName) {
		this.characterName = characterName;
	}
	
}
