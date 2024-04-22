package com.example.btlnhom14.Services;

import java.util.Date;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import  com.example.btlnhom14.Entities.Attempt;
import com.example.btlnhom14.Repositories.AttemptRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AttemptService {

	@Autowired
	private final AttemptRepository attemptRepository;
	
	public ResponseEntity<?> getAttemptById(Long attemptId){
		Attempt attempt = attemptRepository.findById(attemptId).orElse(null);
		if(attempt == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Khong tim thay du lieu ve lan thi");
		}
		
		return ResponseEntity.ok(attempt);
	}
	
	public ResponseEntity<?> createAttempt(Attempt attempt){
		Attempt newAttempt = new Attempt(
                attempt.getUserId(),
                attempt.getExamId(),
                attempt.getStart(),
                attempt.getEnd()
        );
		
		 attemptRepository.save(newAttempt);
		
		return ResponseEntity.ok(newAttempt);
	}
	
	public ResponseEntity<?> editAttempt(Long attemptId, Attempt newAttempt){
		Attempt attempt = attemptRepository.findById(attemptId).orElse(null);
		if(attempt == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Khong tim thay du lieu ve lan thi");
		}
		
		Date date1 = newAttempt.getStart();
		if(date1 != null) {
			attempt.setStart(date1);
		}
		
		Date date2 = newAttempt.getEnd();
		if(date2 != null) {
			attempt.setEnd(date2);
		}
		
		attemptRepository.save(attempt);
	
		return ResponseEntity.ok(attempt);
	}
	
	public ResponseEntity<?> getAttemptByUserId(Long userId){
		List<Attempt>attempts = attemptRepository.findByUserId(userId);
		
		return ResponseEntity.ok(attempts);
	}
	
	 public ResponseEntity<?> getAttemptByExamId(Long examId) {
	        List<Attempt> examAttemptList = attemptRepository.findByExamId(examId);

	        return ResponseEntity.ok(examAttemptList);
	}

	    public ResponseEntity<?> getTotalAttempts(Long examId) {
	        List<Attempt> examAttemptList =attemptRepository.findByExamId(examId);

	        return ResponseEntity.ok(examAttemptList.size());
	    }
}
