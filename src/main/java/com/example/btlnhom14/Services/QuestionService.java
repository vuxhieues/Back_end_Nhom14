package com.example.btlnhom14.Services;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.btlnhom14.Entities.Exam;
import com.example.btlnhom14.Entities.Question;
import com.example.btlnhom14.Repositories.ExamRepository;
import com.example.btlnhom14.Repositories.QuestionRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class QuestionService {

	@Autowired
	private final ExamRepository examRepository;

	@Autowired
	private QuestionRepository questionRepository;
	
	public ResponseEntity<?> getQuestionById(Long questionId){
		Question question = questionRepository.findById(questionId).orElse(null);
		
		if(question == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Khong tim thay cau hoi");
		}
		
		return ResponseEntity.ok(question);
	}
	
	public ResponseEntity<?> getAllQuestion(){
		List<Question>questions = questionRepository.findAll();
		
		return ResponseEntity.ok(questions);
	}
	
	public ResponseEntity<?> getQuestionByExamId(Long examId){
		Exam exam = examRepository.findById(examId).orElse(null);
		if(exam == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ki thi khong ton tai");
		}
		List<Question>questions = questionRepository.findByExamId(examId);
		
		return ResponseEntity.ok(questions);
	}
	
	public ResponseEntity<?> createQuestion(Long examId, Question question){
		Exam exam = examRepository.findById(examId).orElse(null);
		
		if(exam == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ki thi khong ton tai");
		}
		
		Question q = new Question();
		q.setExamId(examId);
		q.setQuestionText(question.getQuestionText());
		q.setOptA(question.getOptA());
		q.setOptB(question.getOptB());
		q.setOptC(question.getOptC());
		q.setOptD(question.getOptD());
		q.setAnswer(question.getAnswer());
		System.out.println(question);
		questionRepository.save(q);
		
		return ResponseEntity.ok(q);
		
	}
	
	public ResponseEntity<?> deleteQuestion(Long questionId){
		Question question = questionRepository.findById(questionId).orElse(null);
		
		if(question == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Khong tim thay cau hoi");
		}
		
		questionRepository.delete(question);
		return ResponseEntity.status(HttpStatus.OK).body("Da xoa cau hoi thanh cong");
	}
	
	public ResponseEntity<?> editQuestion(Long questionId, Question newQuestion){
		Question question = questionRepository.findById(questionId).orElse(null);
		
		if(question == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Khong tim thay cau hoi");
		}
		
		question.setExamId(newQuestion.getExamId());
		question.setQuestionText(newQuestion.getQuestionText());
		question.setOptA(newQuestion.getOptA());
		question.setOptB(newQuestion.getOptB());
		question.setOptC(newQuestion.getOptC());
		question.setOptD(newQuestion.getOptD());
		question.setAnswer(newQuestion.getAnswer());
		
		questionRepository.save(question);
		
		return ResponseEntity.ok(question);
	}
}
