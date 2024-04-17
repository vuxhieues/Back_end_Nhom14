package com.example.btlnhom14.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.btlnhom14.Entities.Exam;
import com.example.btlnhom14.Entities.Result;
import com.example.btlnhom14.Entities.User;
import com.example.btlnhom14.Repositories.ExamRepository;
import com.example.btlnhom14.Repositories.ResultRepository;
import com.example.btlnhom14.Repositories.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ResultService {

	@Autowired
	private final ResultRepository resultRepository;

	@Autowired
	private final UserRepository userRepository;

	@Autowired
	private final ExamRepository examRepository;
	
	
	public ResponseEntity<?> getResultById(Long resultId){
		Result result = resultRepository.findById(resultId).orElse(null);
		
		if(result == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Khong tim thay ket qua");
		}
		return ResponseEntity.ok(result);
	}
	
	public ResponseEntity<?> getAllResult(){
		
		List<Result>results = resultRepository.findAll();
		
		return ResponseEntity.ok(results);
	}
	
	public ResponseEntity<?> getResultByUserId(Long userId){
		User user = userRepository.findById(userId).orElse(null);
		
		if(user == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nguoi dung khong ton tai");
		}
		
		List<Result>results = resultRepository.findByUserId(userId);
		
		return ResponseEntity.ok(results);
	}
	
	public ResponseEntity<?> getResultByExamId(Long examId){
		Exam exam = examRepository.findById(examId).orElse(null);
		
		if(exam == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Khong ton tai bai kiem tra");
		}
		
		List<Result>results = resultRepository.findByExamId(examId);
		return ResponseEntity.ok(results);
	}
	
	public ResponseEntity<?> getResultByExamIdAndUserId(Long examId, Long userId){
		Exam exam = examRepository.findById(examId).orElse(null);
		User user = userRepository.findById(userId).orElse(null);
		
		if(exam == null || user==null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Khong tim thay ket qua");
		}
		
		List<Result>results = resultRepository.findByExamIdAndUserId(examId, userId);
		return ResponseEntity.ok(results);
	}
	
	public ResponseEntity<?> getRultByUserName(String username){
		User user = userRepository.findByUsername(username).orElse(null);
		
		if(user == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nguoi dung khong ton tai");
		}
		
		Long userId = user.getUserId();
		List<Result>results = resultRepository.findByUserId(userId);
		
		return ResponseEntity.ok(results);
	}
	
	public ResponseEntity<?> createResult(Result result){
		Long userId = result.getUserId();
		Long examId = result.getExamId();
		
		User user = userRepository.findById(userId).orElse(null);
		Exam exam = examRepository.findById(examId).orElse(null);
		if(user == null || exam == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Kiem tra lai thong tin");
		}
		List<Result>results = resultRepository.findByExamIdAndUserId(examId, userId);
		if(results.size() > 1) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ban da nop bai thi truoc do");
		}
		
		 Result newResult = new Result(
	                result.getUserId(),
	                result.getExamId(),
	                result.getMark(),
	                result.getStatus()
	        );

	     resultRepository.save(newResult);
		
		return ResponseEntity.ok(result);
	}
	
	public ResponseEntity<?> deleteResult(Long resultId){
		Result result = resultRepository.findById(resultId).orElse(null);
		
		if(result == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Khong tim thay du lieu");
		}
		
		resultRepository.delete(result);
		
		return ResponseEntity.status(HttpStatus.OK).body("Da xoa ket qua thanh cong");
	}
	
	public ResponseEntity<?> editResult(Long resultId, Result newResult){
		Result result = resultRepository.findById(resultId).orElse(null);
		
		if(result == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Khong tim thay du lieu");
		}
		
		int mark = newResult.getMark();
		if(mark >= 0) {
			result.setMark(mark);
		}
		
		String status = newResult.getStatus();
		if(status != null) {
			result.setStatus(status);
		}
		
		resultRepository.save(result);
		
		return ResponseEntity.ok(result);
	}
	
	public ResponseEntity<?> getAverageMark(Long examId){
		Exam exam = examRepository.findById(examId).orElse(null);
		
		if(exam == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Khong tim thay ki thi");
		}
		
		List<Result>results = resultRepository.findByExamId(examId);
		
		int sum=0;
		for(Result i : results) {
			int k = i.getMark();
            sum += k;
        }
		
		double average = (double)sum/(results.size());
		return ResponseEntity.ok(average);
	}
}
