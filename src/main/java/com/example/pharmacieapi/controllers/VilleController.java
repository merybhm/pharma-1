package com.example.pharmacieapi.controllers;


import com.example.pharmacieapi.entity.Ville;
import com.example.pharmacieapi.service.VilleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.print.DocFlavor;
import java.util.List;


@RestController
@RequestMapping("/villes")
@CrossOrigin
public class VilleController {
	@Autowired
	private VilleService service;

	@PostMapping("/add")
	public Ville save(@RequestBody Ville v) {
		return service.addVille(v);
	}

	@GetMapping("/all")
	public List<Ville> findAllVille(){

		return service.findAllVille();
	}

	@GetMapping("/ville/id={id}")
	public Ville findVilleById(@PathVariable int id){

		return service.findVilleById(id);
	}

	@PutMapping("/updateVille/id={id}")
	public Ville updateVille(@RequestBody Ville v,@PathVariable int id){

		return service.updateVille(v,id);
	}

	@DeleteMapping("/deleteVille/id={id}")
	public String deleteClient(@PathVariable int id){

		return service.deleteVille(id);
	}

	@GetMapping("/NbrPharmacie")
	public List findNbrPharmacieVille(){
		return service.findNbrPharmacieVille();
	}

	@GetMapping("/NbrPharmacieEnGarde")
	public List findNbrPharmacieEnGardeVille(){
		return service.findNbrPharmacieEnGardeVille();
	}
}
