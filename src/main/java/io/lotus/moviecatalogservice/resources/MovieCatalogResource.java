package io.lotus.moviecatalogservice.resources;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import io.lotus.moviecatalogservice.models.CatalogItem;
import io.lotus.moviecatalogservice.models.Movie;
import io.lotus.moviecatalogservice.models.MovieRating;
import io.lotus.moviecatalogservice.models.UserRating;


@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private WebClient.Builder builder;
	
	@RequestMapping("/{userId}")
	public List<CatalogItem> getCatalogItem(@PathVariable("userId") String userId){
		
		
		/*Movie movie = restTemplate.getForObject("http://localhost:8081/movies/11", Movie.class); */
		WebClient.Builder builder = WebClient.builder();
		
		UserRating userRating = restTemplate.getForObject("http://movie-rating-service/ratings/users/" + "123", UserRating.class);
		List<MovieRating> ratings = userRating.getUserRating();

		/*return ratings.stream().map(rating -> {
			Movie movie = restTemplate.getForObject("http://localhost:8081/movies/11" + rating.getRating(), Movie.class);
			new CatalogItem(movie.getMovieName(),"Directed by Steven Spielberg", rating.getRating());
			
		}).collect(Collectors.toList()); */
		
		return ratings.stream().map(rating -> {
			Movie movie = restTemplate.getForObject("http://movie-info-service/movies/" + rating.getRating(), Movie.class);
			
			/*Movie movie = builder.build()
			.get()
			.uri("http://localhost:8082/movies/" + rating.getRating())
			.retrieve()
			.bodyToMono(Movie.class)
			.block(); */
			
			return new CatalogItem(movie.getMovieName(),"Directed by Steven Spielberg",rating.getRating());
			}).collect(Collectors.toList());
			
		
	
	}

}
