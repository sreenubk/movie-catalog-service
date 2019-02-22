package io.lotus.moviecatalogservice.models;

public class MovieRating {
	
	private String movieId;
	private String rating;
	
	public MovieRating() {
		
	}
	
	public MovieRating(String movieId, String rating) {
		this.movieId=movieId;
		this.rating=rating;
	}
	public String getMovieId() {
		return movieId;
	}
	public void setMovieId(String movieId) {
		this.movieId = movieId;
	}
	public String getRating() {
		return rating;
	}
	public void setRating(String rating) {
		this.rating = rating;
	}

}
