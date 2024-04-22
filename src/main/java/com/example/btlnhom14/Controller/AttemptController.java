package com.example.btlnhom14.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.btlnhom14.Entities.Attempt;
import com.example.btlnhom14.Services.AttemptService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/attempt")
@CrossOrigin("*")
public class AttemptController {

	@Autowired
	private final AttemptService attemptService;
	
	@GetMapping("/{attemptId}")
	public ResponseEntity<?> getAttemptById(@PathVariable("attemptId") Long attemptId){
		return attemptService.getAttemptById(attemptId);
	}
	
	@GetMapping("/exam/{examId}")
	public ResponseEntity<?> getAttemptByExamId(@PathVariable("examId") Long examId){
		return attemptService.getAttemptByExamId(examId);
	}
	
	@GetMapping("/user/{userId}")
	public ResponseEntity<?> getAttemptByUserId(@PathVariable("userId") Long userId){
		return attemptService.getAttemptByUserId(userId);
	}
	
	@PostMapping("/create-attempt")
	public ResponseEntity<?> createAttempt(@RequestBody Attempt attempt){
		return attemptService.createAttempt(attempt);
	}
	
	@PutMapping("/edit-attempt/{attemptId}")
	public ResponseEntity<?> editAttempt(@PathVariable("attemptId") Long attemptId, @RequestBody Attempt newAttempt){
		return attemptService.editAttempt(attemptId, newAttempt);
	}
	
	@GetMapping("/exam/{examId}/total")
	public ResponseEntity<?> getTotalAttempt(@PathVariable("examId") Long examId){
		return attemptService.getTotalAttempts(examId);
	}
}
