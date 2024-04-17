package com.example.btlnhom14.Services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.btlnhom14.Entities.Attempt;
import com.example.btlnhom14.Entities.Exam;
import com.example.btlnhom14.Entities.Result;
import com.example.btlnhom14.Entities.Statistic;
import com.example.btlnhom14.Entities.User;
import com.example.btlnhom14.Repositories.AttemptRepository;
import com.example.btlnhom14.Repositories.ExamRepository;
import com.example.btlnhom14.Repositories.ResultRepository;
import com.example.btlnhom14.Repositories.UserRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class StatisticService {

	@Autowired
	private final UserRepository userRepository;

	@Autowired
	private final ExamRepository examRepository;

	@Autowired
	private final AttemptRepository attemptRepository;

	@Autowired
	private final ResultRepository resultRepository;
	
	public ResponseEntity<?> getStatistic(){
		List<Exam>exams = examRepository.findAll();
		List<Statistic>Stats = new ArrayList<>();
		
		for(Exam e : exams) {
			Long examId = e.getExamId();
			List<User>users = userRepository.findByRoleContaining("USER");
			List<Attempt>attempts = attemptRepository.findByExamId(examId);
			List<Result>results = resultRepository.findByExamId(examId);
			
			double complete;
			
			if(users.size() > 0) {
				complete = (double)results.size()/users.size();
			}
			else {
				complete = 0;
			}
			
			complete*=100;
			if(complete > 100) {
				complete = 100;
			}
			
			int totalUser = attempts.size();
			
			int totalMark = 0;
			for(Result i : results) {
				totalMark+=i.getMark();
			}
			
			double average;
			if(results.size() > 0) {
				average = (double)totalMark/results.size();
			}
			else {
				average = 0;
			}
			
			Statistic statistic = new Statistic();
			statistic.setExamName(e.getExamName());
			statistic.setAverageMark(average);
			statistic.setComplete(complete);
			statistic.setTotalJoin(totalUser);
			
			Stats.add(statistic);
		}
		return ResponseEntity.ok(Stats);
	}
}
