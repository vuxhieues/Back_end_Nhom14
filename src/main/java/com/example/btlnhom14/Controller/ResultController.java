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

import com.example.btlnhom14.Entities.Result;
import com.example.btlnhom14.Services.ResultService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/result")
@CrossOrigin("*")
public class ResultController {

	@Autowired
	private final ResultService resultService;
	
	@GetMapping("/{resultId}")
	public ResponseEntity<?> getResultbyId(@PathVariable("resultId") Long resultId){
		return resultService.getResultById(resultId);
	}
	
	@GetMapping("/exam/{examId}")
	public ResponseEntity<?> getResultbyExamId(@PathVariable("examId") Long examId){
		return resultService.getResultByExamId(examId);
	}
	
	@GetMapping("/user/{userId}")
	public ResponseEntity<?> getResultbyUserId(@PathVariable("userId") Long userId){
		return resultService.getResultByUserId(userId);
	}
	
	@GetMapping("/exam/{examId}/user/{userId}")
	public ResponseEntity<?> getResultbyExamIdAndUserId(@PathVariable("examId") Long examId, @PathVariable("userId") Long userId){
		return resultService.getResultByExamIdAndUserId(examId, userId);
	}
	
	@GetMapping("/all-results")
	public ResponseEntity<?> getAllResult(){
		return resultService.getAllResult();
	}
	
	@GetMapping("/mark/{examId}")
	public ResponseEntity<?> getAverageMark(@PathVariable("examId") Long examId){
		return resultService.getAverageMark(examId);
	}
	
	@GetMapping("/{username}")
	public ResponseEntity<?> getResultByUserName(@PathVariable("username") String username){
		return resultService.getRultByUserName(username);
	}
	
	@PostMapping("/creat-result")
	public ResponseEntity<?> creatResult(@RequestBody Result result){
		return resultService.createResult(result);
	}
	
	@DeleteMapping("/delete-result/{resultId}")
	public ResponseEntity<?> deleteResult(@PathVariable("resultId") Long resultId){
		return resultService.deleteResult(resultId);
	}
	
	@PutMapping("/edit-exam/{resultId}")
	public ResponseEntity<?> editResult(@PathVariable("resultId") Long resultId, @RequestBody Result newreResult){
		return resultService.editResult(resultId, newreResult);
	}
	
	
}
