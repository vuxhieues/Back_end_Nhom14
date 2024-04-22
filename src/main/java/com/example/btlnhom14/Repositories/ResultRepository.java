package com.example.btlnhom14.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.btlnhom14.Entities.Result;

@Repository
public interface ResultRepository extends JpaRepository<Result, Long>{
	List<Result> findByExamId(Long examId);
	
	List<Result> findByUserId(Long userId);
	
	List<Result> findByExamIdAndUserId(Long examId, Long userId);
}
