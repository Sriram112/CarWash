package com.casestudy.user.service;

import com.casestudy.user.repository.RatingRepo;
import com.casestudy.user.model.Ratings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RatingsService {
    @Autowired
    RatingRepo rr;


    public List<Ratings> getallRatings(){
        return rr.findAll();
    }

    public Ratings addRating(Ratings ratings){
        return rr.save(ratings);
    }

    public List<Ratings> washerSpecific(String washerName){
        List<Ratings> washerSpecificRatings=rr.findAll().stream().filter(x -> x.getWasherName().contains(washerName)).collect(Collectors.toList());
        return washerSpecificRatings;
    }

    public String deleteRating(int id){
        rr.deleteById(id);
        return "Rating with ID -> "+id+" deleted successfully";
    }}
