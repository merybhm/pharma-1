package com.pharma.repository;

import com.pharma.entity.DatePharm;
import com.pharma.entity.DatePharmPk;
import com.pharma.entity.Garde;
import com.pharma.entity.Pharmacie;
import org.springframework.data.jpa.repository.JpaRepository;


public interface DatePharmRepository  extends JpaRepository<DatePharm, DatePharmPk> {
    DatePharm findByPharmacie(Pharmacie pharmacie);
 

}
