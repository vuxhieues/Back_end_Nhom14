package com.example.btlnhom14.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.btlnhom14.Entities.Statistic;
@Repository
public interface StatisticRepository extends JpaRepository<Statistic, Long>{
	
}
