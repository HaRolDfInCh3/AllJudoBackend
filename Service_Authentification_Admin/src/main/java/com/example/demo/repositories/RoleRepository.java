package com.example.demo.repositories;



import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.entities.Role;

public interface RoleRepository extends MongoRepository<Role, String> {
	public Role findById(int id2);
	public Boolean existsById(int id2);
	public Role findByDesignation(String designation);
	public Boolean existsByDesignation(String designation);
	public Role findByIdMongo(String idMongo);
	public Boolean existsByIdMongo(String idMongo);
	public Role deleteById(int id2);
}
