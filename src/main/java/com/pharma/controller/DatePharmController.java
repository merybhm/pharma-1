package com.pharma.controller;

import com.pharma.entity.DatePharm;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.pharma.entity.Garde;
import com.pharma.entity.Pharmacie;
import com.pharma.entity.TypeDeGarde;
import com.pharma.repository.DatePharmRepository;
import com.pharma.repository.GardeRepository;
import com.pharma.repository.PharmaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController

public class DatePharmController {
	  @Autowired
	    DatePharmRepository datePharmRepository;
	    @Autowired
	    PharmaRepository pharmacieRepository;
	    @Autowired
	    GardeRepository gardeRepository;


	    @GetMapping("/pharmacie_of_garde/{id}/{type}")
	    public String pahramcieGard(@PathVariable(value = "id") int id,@PathVariable(value = "type") String type){
	        Pharmacie pharmacie = pharmacieRepository.findById(id);
	        Garde garde;
	        if(type.equals("J")){
	             garde = gardeRepository.findByType(TypeDeGarde.jour);
	             if(garde == null){
	                 garde = new Garde(TypeDeGarde.jour);
	                 gardeRepository.save(garde);
	             }
	        }else{
	            garde = gardeRepository.findByType(TypeDeGarde.nuit);
	            if(garde == null){
	                garde = new Garde(TypeDeGarde.nuit);
	                gardeRepository.save(garde);
	            }

	        }
	        DatePharm datePharm = new DatePharm(null,pharmacie,garde);
	        datePharmRepository.save(datePharm);
	        return "yeees";
	    }


	    @GetMapping("/pharmacie_of_garde/fin/{id}")
	    public String finGard(@PathVariable(value = "id") int id){
	        Pharmacie pharmacie = pharmacieRepository.findById(id);
	        DatePharm datePharm = datePharmRepository.findByPharmacie(pharmacie);
	        if (datePharm.getDateFin() == null){
	        	datePharm.setDateFin(new Date());
	            datePharmRepository.save(datePharm);

	        }
	        return "ok";
	    }
	  
	     
	    @GetMapping("/pharmacie_of_garde")
	    public List<Pharmacie> allGard(){
	        List<Pharmacie> pharmacieList = new ArrayList<>();
	        for(DatePharm d:datePharmRepository.findAll()){
	            if(d.getDateFin() == null)
	                pharmacieList.add(d.getPharmacie());
	        }
	        return pharmacieList;
	    }
}