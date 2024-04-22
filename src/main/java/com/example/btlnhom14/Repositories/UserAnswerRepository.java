package com.example.btlnhom14.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.btlnhom14.Entities.UserAnswer;

@Repository
public interface UserAnswerRepository extends JpaRepository<UserAnswer, Long>{
	List<UserAnswer> findByExamId(Long examId);
	
	List<UserAnswer> findByUserId(Long userId);
	
	List<UserAnswer> findByQuestionId(Long questionId);
	
	List<UserAnswer> findByExamIdAndUserId(Long examId, Long userId);
	
	UserAnswer findByQuestionIdAndUserId(Long questionId, Long userId);
}
