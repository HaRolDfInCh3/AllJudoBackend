package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.entities.User;

public interface UserRepository extends MongoRepository<User, String> {
	public User findById(int id2);
	public Boolean existsById(int id2);
	public User findByIdMongo(String Id);
	public Boolean existsByUsername(String u);
	public List<User> findByUsername(String u);
	public Boolean existsByIdMongo(String Id);
	public User deleteById(int id2);
}
