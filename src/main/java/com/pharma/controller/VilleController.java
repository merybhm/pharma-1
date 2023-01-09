package com.pharma.controller;


import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.pharma.entity.Pharmacie;
import com.pharma.entity.Zone;
import org.hibernate.sql.ast.tree.expression.Collation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pharma.entity.Ville;
import com.pharma.repository.VilleRepository;


@RestController
@RequestMapping("villes")
public class VilleController {
	@Autowired
	private VilleRepository villeRepository;
	
	
	@PostMapping("/save")
	public void save(@RequestBody Ville ville){
	villeRepository.save(ville);
	}
	@DeleteMapping("/delete/{id}")
	public void delete(@PathVariable (required = true) String id) {
	Ville V =villeRepository.findById(Integer.parseInt(id));
	villeRepository.delete(V);
	}
	@GetMapping("/all")
	public List<Ville> findAll(){
	return villeRepository.findAll();
	}
	@GetMapping("/ville/{ville}")
	public Collection<Pharmacie> getPharmacieByVille(@PathVariable(value = "ville") String v){
		Ville ville = villeRepository.findFirstByNomContains(v);
		Collection<Zone> zones = ville.getZones();
		List<Pharmacie> pharmacieList = new ArrayList<>();
		for(Zone c:zones){
			for (Pharmacie ph:c.getPharmacies()){
				pharmacieList.add(ph);
			}
		}
		return pharmacieList;
	}
	@GetMapping("/count")
	public Map<String, Integer> count() {
		Map<String, Integer> map = new HashMap<>();
		for (Ville v : villeRepository.findAll()) {
			map.put(v.getNom(), v.getZones().size());
		}
		return map;
	}

}
