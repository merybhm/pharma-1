package com.pharma.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.pharma.entity.Zone;

public interface ZoneRepository extends JpaRepository<Zone, Integer>{
	Zone findById(int id);
	Zone findByNomContains(String nom); 

}
