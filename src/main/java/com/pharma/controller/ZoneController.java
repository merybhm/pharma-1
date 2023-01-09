package com.pharma.controller;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.pharma.entity.Pharmacie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pharma.entity.Zone;
import com.pharma.repository.VilleRepository;
import com.pharma.repository.ZoneRepository;
@RestController
@RequestMapping("zones")
public class ZoneController {
	@Autowired
	private ZoneRepository zoneRepository;
	@Autowired
	private VilleRepository villeRepository;
	
	@PostMapping("/save")
	public void save(@RequestBody Zone zone){
		zone.setVille(villeRepository.findById(zone.getVille().getId()));
	zoneRepository.save(zone);
	}
	
	@DeleteMapping("/delete/{id}")
	public void delete(@PathVariable(required=true) String id) {
		Zone zone = zoneRepository.findById(Integer.parseInt(id));
		zoneRepository.delete(zone);
	}
	@GetMapping("/all")
	public List<Zone> findAll(){
		return zoneRepository.findAll();
	}

	@GetMapping("/zone/{zone}")
	public Collection<Pharmacie> getPhByZone(@PathVariable(value = "zone") String zone){
		Zone zone1 = zoneRepository.findByNomContains(zone);
		return zone1.getPharmacies();
	}
	@GetMapping("/count")
	public Map<String, Integer> count() {
		Map<String, Integer> map = new HashMap<>();
		for (Zone z : zoneRepository.findAll()) {
			map.put(z.getNom(), z.getPharmacies().size());
		}
		return map;
	}
}
