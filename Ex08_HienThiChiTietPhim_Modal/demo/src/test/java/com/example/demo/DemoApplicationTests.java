package com.example.demo;

import com.example.demo.entity.Movie;
import com.example.demo.model.enums.MovieType;
import com.example.demo.repository.MovieRepository;
import com.github.javafaker.Faker;
import com.github.slugify.Slugify;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@SpringBootTest
class DemoApplicationTests {

	@Autowired
	private MovieRepository movieRepository;
	@Test
	void save_movies() {
		Faker faker = new Faker();
		Slugify slugify = Slugify.builder().build();
		Random rd = new Random();

		for (int i = 0; i < 150; i++) {
			// Tao entity
			String name = faker.funnyName().name();
			String thumbnail = "https://placehold.co/600x400?text=" + name.substring(0, 1).toUpperCase();
			Boolean status = faker.bool().bool();

			int rdIndexType = rd.nextInt(MovieType.values().length);
			MovieType type = MovieType.values()[rdIndexType];

			Movie movie = Movie.builder()
					.name(name)
					.slug(slugify.slugify(name))
					.description(faker.lorem().paragraph())
					.thumbnail(thumbnail)
					.releaseYear(faker.number().numberBetween(1990, 2021))
					.status(status)
					.trailer("https://www.youtube.com/embed/W_0AMP9yO1w?si=TfsLefIJpX88g08l")
					.rating(faker.number().randomDouble(1, 5, 10))
					.type(type)
					.createdAt(LocalDateTime.now())
					.updatedAt(LocalDateTime.now())
					.publishedAt(status ? LocalDateTime.now() : null)
					.build();

			// Luu vao DB
			movieRepository.save(movie)
			;
		}
	}

	@Test
	void testQuery() {
//		Movie movie =  movieRepository.findByName("Stan Still");
//		System.out.println(movie);

		// Sắp xếp
//		List<Movie> movieSortByRating = movieRepository.findByRatingLessThan(8.0, Sort.by(Sort.Direction.DESC, "rating"));
//		movieSortByRating.forEach(movie -> System.out.println(movie.getName() + movie.getRating()));

		// Phân trang
		Pageable pageable = PageRequest.of(0, 5, Sort.by("rating").descending());
		Page<Movie> moviePage = movieRepository.findByNameContaining("a", pageable);
		System.out.println("Total pages: " + moviePage.getTotalPages());
		System.out.println("Total element: " + moviePage.getTotalElements());
		moviePage.getContent().forEach(movie -> System.out.println(movie.getName() + movie.getRating()));

	}

}
