package com.example.btlnhom14.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.btlnhom14.Entities.Question;
import com.example.btlnhom14.Services.QuestionService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/question")
@RequiredArgsConstructor
@CrossOrigin("*")
public class QuestionController {

	@Autowired
	private final QuestionService questionService;
	
	@GetMapping("/{questionId}")
	public ResponseEntity<?> getQuestionById(@PathVariable("questionId") Long questionId){
		return questionService.getQuestionById(questionId);
	}
	
	@GetMapping("/all-question")
	public ResponseEntity<?> getAllQuestion(){
		return questionService.getAllQuestion();
	}
	
	@GetMapping("/exam/{examId}")
	public ResponseEntity<?> getQuestionByExamId(@PathVariable("examId") Long examId){
		return questionService.getQuestionByExamId(examId);
	}
	
	@PostMapping("/create-question/{examId}")
	public ResponseEntity<?> creatQuestion(@PathVariable("examId") Long examId,@RequestBody Question question){
		return questionService.createQuestion(examId, question);
	}
	
	@DeleteMapping("/delete-question/{questionId}")
	public ResponseEntity<?> deleteQuestion(@PathVariable("questionId") Long questionId){
		return questionService.deleteQuestion(questionId);
	}
	
	@PutMapping("/edit-question/{questionId}")
	public ResponseEntity<?> editQuestion(@PathVariable("questionId") Long questionId,@RequestBody Question newQuestion){
		return questionService.editQuestion(questionId, newQuestion);
	}
}
