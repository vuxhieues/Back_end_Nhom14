package com.example.btlnhom14.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.btlnhom14.Entities.UserAnswer;
import com.example.btlnhom14.Services.UserAnswerService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user-answer")
@CrossOrigin("*")
public class UserAnswerController {

	@Autowired
	private final UserAnswerService userAnswerService;
	
	@GetMapping("/{userAnswerId}")
	public ResponseEntity<?> getUserAnswerById(@PathVariable("userAnswerId") Long userAnswerId){
		return userAnswerService.getUserAnswerById(userAnswerId);
	}
	
	@GetMapping("/user/{userId}")
	public ResponseEntity<?> getUserAnswerByUserId(@PathVariable("userId") Long userId){
		return userAnswerService.getAnswerByUserId(userId);
	}
	
	@GetMapping("/question/{questionId}")
	public ResponseEntity<?> getUserAnswerByQuestionId(@PathVariable("questionId") Long questionId){
		return userAnswerService.getAnswerByQuestionId(questionId);
	}
	
	@GetMapping("/exam/{examId}")
	public ResponseEntity<?> getUserAnswerByExamId(@PathVariable("examId") Long examId){
		return userAnswerService.getAnswerByExamId(examId);
	}
	
	@GetMapping("/exam/{examId}/user/{userId}")
	public ResponseEntity<?> getUserAnswerByExamIdAndUserId(@PathVariable("userId") Long userId, @PathVariable("examId") Long examId){
		return userAnswerService.getAnswerByUserIdAndExamId(userId, examId);
	}
	
	@PostMapping("/create-user-answer")
	public ResponseEntity<?> createUserAnswer(@RequestBody UserAnswer userAnswer){
		return userAnswerService.createUserAnswer(userAnswer);
	}
	
	@GetMapping("/{userAnswerId}")
	public ResponseEntity<?> deleteUserAnswer(@PathVariable("userAnswerId") Long userAnswerId){
		return userAnswerService.deleteUserAnswer(userAnswerId);
	}
}
