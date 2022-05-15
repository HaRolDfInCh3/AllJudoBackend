package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.entities.Club;

public interface ClubRepository extends MongoRepository<Club, String> {
	public Club findById(int id2);
	public Boolean existsById(int id2);
	public List<Club> findByClub(String club);
	public Boolean existsByClub(String club);
	public Club findByIdMongo(String idMongo);
	public Boolean existsByIdMongo(String idMongo);
	public Club deleteById(int id2);
}
