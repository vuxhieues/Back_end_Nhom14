package com.example.btlnhom14.Controller;



import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.btlnhom14.Entities.UserAnswer;
import com.example.btlnhom14.Services.UserAnswerService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user-answer")
@CrossOrigin("*")
public class UserAnswerController {


	private final UserAnswerService userAnswerService;

	@PostMapping("/create-user-answer")
	public ResponseEntity<?> createUserAnswer(@RequestBody UserAnswer userAnswer) {
		return userAnswerService.createUserAnswer(userAnswer);
	}


	@GetMapping("/{userAnswerId}")
	public ResponseEntity<?> getUserAnswerById(@PathVariable("userAnswerId") Long userAnswerId) {
		return userAnswerService.getUserAnswerById(userAnswerId);
	}


	@GetMapping("/user/{userId}")
	public ResponseEntity<?> getUserAnswerByUserId(@PathVariable("userId") Long userId) {
		return userAnswerService.getAnswerByUserId(userId);
	}


	@GetMapping("/exam/{examId}")
	public ResponseEntity<?> getUserAnswerByExamId(@PathVariable("examId") Long examId) {
		return userAnswerService.getAnswerByExamId(examId);
	}

	@GetMapping("/question/{questionId}")
	public ResponseEntity<?> getUserAnswerByQuestionId(@PathVariable("questionId") Long questionId) {
		return userAnswerService.getAnswerByQuestionId(questionId);
	}


	@GetMapping("/exam/{examId}/user/{userId}")
	public ResponseEntity<?> getUserAnswerInAnExam(@PathVariable("examId") Long examId, @PathVariable("userId") Long userId) {
		return userAnswerService.getAnswerByUserIdAndExamId(examId, userId);
	}


//	@DeleteMapping("/delete-user-answer/{userAnswerId}")
//	public ResponseEntity<?> deleteUserAnswer(@PathVariable("userAnswerId") Long userAnswerId) {
//		return userAnswerService.deleteUserAnswer(userAnswerId);
//	}
}
