package com.pharma.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pharma.entity.Ville;


public interface VilleRepository extends JpaRepository<Ville, Integer>{
	Ville findById(int id);
	Ville findFirstByNomContains(String nom); 
 

}
