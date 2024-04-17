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

import com.example.btlnhom14.Entities.Exam;
import com.example.btlnhom14.Services.ExamService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/exam")
@CrossOrigin("*")
public class ExamController {

	@Autowired
	private final ExamService examService;
	
	@GetMapping("/{examId}")
	public ResponseEntity<?> getExambyId(@PathVariable("examId") Long examId){
		return examService.getExamById(examId);
	}
	
	@GetMapping("/{examName}")
	public ResponseEntity<?> getExambyName(@PathVariable("examName") String examName){
		return examService.getExamByExamName(examName);
	}
	
	@GetMapping("/{examType}")
	public ResponseEntity<?> getExambyType(@PathVariable("examType") String examType){
		return examService.getExamByExamType(examType);
	}
	
	@GetMapping("/all-exams")
	public ResponseEntity<?> getAllExam(){
		return examService.getAllExams();
	}
	
	@PostMapping("/create-exam")
	public ResponseEntity<?> createExam(@RequestBody Exam exam){
		return examService.createExam(exam);
	}
	
	@DeleteMapping("/delete-exam/{examId}")
	public ResponseEntity<?> deleteExam(@PathVariable("examId") Long examId){
		return examService.deleteExamById(examId);
	}
	
	@PutMapping("/edit-exam/{examId}")
	public ResponseEntity<?> editExam(@PathVariable("examId") Long examId, @RequestBody Exam newExam){
		return editExam(examId, newExam);
	}
}
