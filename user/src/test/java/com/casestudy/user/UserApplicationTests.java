package com.casestudy.user;

import com.casestudy.user.model.Car;
import com.casestudy.user.model.OrderDetails;
import com.casestudy.user.model.Ratings;
import com.casestudy.user.repository.RatingRepo;
import com.casestudy.user.service.RatingsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class UserApplicationTest{

	@Autowired
	RatingsService rs;

	@MockBean
	RatingRepo rr;

	@Test
	public void addRatingTest(){
		Ratings ratings = new Ratings(1,"w1","good",5);
		when(rr.save(ratings)).thenReturn(ratings);
		assertEquals(ratings,rs.addRating(ratings));
	}

	@Test
	public void getAllRatingsTest(){
		when(rr.findAll()).thenReturn(Stream.of(

						new Ratings(1,"w1","good",5),
						new Ratings(1,"w1","good",5),
						new Ratings(1,"w1","good",5)
				)
				.collect(Collectors.toList()));
		assertEquals(3,rs.getallRatings().size());
	}

	@Test
	public void washerSpecificRatingsTest(){
		Ratings ratings = new Ratings(1,"w1","good",5);
		when(rr.findAll()).thenReturn(Stream.of(

						new Ratings(1,"w2","good",5),
						new Ratings(1,"w1","good",5),
						new Ratings(1,"w1","good",5)
				)
				.collect(Collectors.toList()));

		assertEquals(2,rs.washerSpecific("w1").size());

	}

}