package com.pharma.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pharma.entity.Pharmacie;
import com.pharma.entity.Ville;
import com.pharma.entity.Zone;
import com.pharma.repository.PharmaRepository;
@RestController
@RequestMapping("pharmacies")
public class PharmaController {
	@Autowired
	private PharmaRepository pharmacieRepository;
	
	@PostMapping("/save")
	public void save(@RequestBody Pharmacie pharmacie){
	pharmacieRepository.save(pharmacie);
	}
	
	@DeleteMapping("/delete/{id}")
	public void delete(@PathVariable(required=true) String id) {
		Pharmacie pharma = pharmacieRepository.findById(Integer.parseInt(id));
		pharmacieRepository.delete(pharma);
		
	}
	@GetMapping("/all")
	public List<Pharmacie> findAll(){
		return pharmacieRepository.findAll();
	}
	@GetMapping(value = "/count")
	public long countPharmacie() {
	return pharmacieRepository.count();
	
	}

	@PostMapping("/ville")
	public Pharmacie getPharmacieByVille(@RequestBody Ville ville) {
		return pharmacieRepository.findByVille(ville);
		
	}
	
	
	@PostMapping("/zone")
	public Pharmacie getPharmacieByVille(@RequestBody Zone zone) {
		return pharmacieRepository.findByZone(zone);
		
	}
	
	
	
}
