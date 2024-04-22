package com.example.btlnhom14.Services;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.btlnhom14.Entities.Question;
import com.example.btlnhom14.Entities.UserAnswer;
import com.example.btlnhom14.Repositories.QuestionRepository;
import com.example.btlnhom14.Repositories.UserAnswerRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserAnswerService {

	@Autowired
	private final UserAnswerRepository userAnswerRepository;

	@Autowired
	private final QuestionRepository questionRepository;
	
	public ResponseEntity<?> getUserAnswerById(Long userAnswerId){
		UserAnswer userAnswer = userAnswerRepository.findById(userAnswerId).orElse(null);
		
		if(userAnswer == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Khong tim thay cau tra loi");
		}
		
		return ResponseEntity.ok(userAnswer);
	}
	
	public ResponseEntity<?> getAnswerByUserId(Long userId){
		List<UserAnswer>userAnswers = userAnswerRepository.findByUserId(userId);
		
		return ResponseEntity.ok(userAnswers);
	}
	
	public ResponseEntity<?> getAnswerByExamId(Long examId){
		List<UserAnswer>userAnswers = userAnswerRepository.findByExamId(examId);
		
		return ResponseEntity.ok(userAnswers);
	}
	
	public ResponseEntity<?> getAnswerByQuestionId(Long questionId){
		List<UserAnswer>userAnswers = userAnswerRepository.findByQuestionId(questionId);
		
		return ResponseEntity.ok(userAnswers);
	}
	
	public ResponseEntity<?> getAnswerByUserIdAndExamId(Long userId, Long examId){
		List<UserAnswer>userAnswers = userAnswerRepository.findByExamIdAndUserId(examId, userId);
		
		return ResponseEntity.ok(userAnswers);
	}
	
	public ResponseEntity<?> createUserAnswer(UserAnswer userAnswer){
		UserAnswer newUserAnswer = new UserAnswer(
				userAnswer.getUserId(),
				userAnswer.getExamId(),
				userAnswer.getQuestionId(),
				userAnswer.getSelected()
		);

		userAnswerRepository.save(newUserAnswer);

		Question question = questionRepository.findById(newUserAnswer.getQuestionId()).orElseThrow();
		String correctAnswer = question.getAnswer();

		if(correctAnswer.equalsIgnoreCase(userAnswer.getSelected())) {
			return ResponseEntity.ok(true);
		}
		return ResponseEntity.ok(false);
	}

//	public ResponseEntity<?> deleteUserAnswer(Long userAnswerId) {
//		UserAnswer userAnswer = userAnswerRepository.findById(userAnswerId).orElseThrow();
//
//		userAnswerRepository.delete(userAnswer);
//
//		return ResponseEntity.ok("Xoa cau tra loi thanh cong");
//	}
}
