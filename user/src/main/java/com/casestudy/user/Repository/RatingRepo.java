package com.casestudy.user.Repository;

import com.casestudy.user.model.Ratings;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RatingRepo extends MongoRepository<Ratings,Integer> {
}
