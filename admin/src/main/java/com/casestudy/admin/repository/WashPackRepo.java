package com.casestudy.admin.repository;

import com.casestudy.admin.model.WashPacks;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface WashPackRepo extends MongoRepository<WashPacks, String> {
}