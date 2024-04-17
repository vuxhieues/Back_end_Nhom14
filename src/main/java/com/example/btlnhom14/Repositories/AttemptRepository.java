package com.example.btlnhom14.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.btlnhom14.Entities.Attempt;

@Repository
public interface AttemptRepository extends JpaRepository<Attempt, Long>{
	List<Attempt> findByUserId(Long userId);
	
	List<Attempt> findByExamId(Long examId);
}
