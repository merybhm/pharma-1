package com.pharma.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pharma.entity.Garde;
import com.pharma.entity.TypeDeGarde;



public interface GardeRepository extends JpaRepository<Garde, Integer>{
	Garde findById(int id);
	Garde findByType(TypeDeGarde typeGarde);
	
}
