package com.example.btlnhom14.Services;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.btlnhom14.Entities.Exam;
import com.example.btlnhom14.Repositories.ExamRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ExamService {

	@Autowired
	private final ExamRepository examRepository;
	
	public ResponseEntity<?> getExamById(Long examId){
		Exam exam = examRepository.findById(examId).orElse(null);
		
		if(exam == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Khong tim thay bai kiem tra");
		}
		
		return ResponseEntity.ok(exam);
	}
	
	public ResponseEntity<?> deleteExamById(Long examId){
		Exam exam = examRepository.findById(examId).orElse(null);
		
		if(exam == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Khong tim thay bai kiem tra");
		}
		
		examRepository.deleteById(examId);
		
		return ResponseEntity.status(HttpStatus.OK).body("Da xoa bai kiem tra thanh cong");
	}
	
	public ResponseEntity<?> getAllExams(){
		List<Exam>exams = examRepository.findAll();
		
		return ResponseEntity.ok(exams);
	}
	
    public ResponseEntity<?> createExam(Exam exam) {
    	Exam newExam = new Exam();
        newExam.setExamName(exam.getExamName());
        newExam.setDescription(exam.getDescription());
        newExam.setExamType(exam.getExamType());
        newExam.setStart(exam.getStart());
        newExam.setEnd(exam.getEnd());
        
        examRepository.save(newExam);

        return ResponseEntity.ok(newExam);
    }

	public ResponseEntity<?> getExamByExamName(String examName){
		List<Exam>exams = examRepository.findByExamName(examName);

		return ResponseEntity.ok(exams);
	}

	public ResponseEntity<?> editExam(Long examId, Exam newExam) {
		Exam exam = examRepository.findById(examId).orElse(null);

		if (exam == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("ko tim thay exam theo id");
		}

		exam.setExamName(newExam.getExamName());
		exam.setDescription(newExam.getDescription());
		exam.setExamType(newExam.getExamType());
		exam.setStart(newExam.getStart());
		exam.setEnd(newExam.getEnd());

		examRepository.save(exam);

		return ResponseEntity.status(HttpStatus.OK)
				.body("da sua exam");

	}
	
	public ResponseEntity<?> getExamByExamType(String examType){
		List<Exam>exams = examRepository.findByExamType(examType);
		
		return ResponseEntity.ok(exams);
	}
}
