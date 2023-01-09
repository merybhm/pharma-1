package com.pharma.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.pharma.entity.Pharmacie;
import com.pharma.entity.Ville;
import com.pharma.entity.Zone;
public interface PharmaRepository extends JpaRepository<Pharmacie, Integer>{
	Pharmacie findById(int id);
	Pharmacie findByNom(String nom);
	Pharmacie findByZone(Zone zone);
	Pharmacie findByVille(Ville ville);


	}


