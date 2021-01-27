package com.java.rms.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.java.rms.entity.Rate;
@Repository
public interface RateRepository extends JpaRepository<Rate, Long>{

}
