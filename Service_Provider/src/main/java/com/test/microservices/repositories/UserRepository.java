package com.test.microservices.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.test.microservices.pojos.User;

public interface UserRepository extends MongoRepository<User, String> {
	public User findById(int id2);
	public Boolean existsById(int id2);
	public User findByIdMongo(String Id);
	public Boolean existsByIdMongo(String Id);
	public User deleteById(int id2);
}
