package com.example.btlnhom14.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.btlnhom14.Services.StatisticService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/statistic")
@RequiredArgsConstructor
@CrossOrigin("*")
public class StatisticController {

	@Autowired
	private final StatisticService statisticService;
	
	@GetMapping("/get-all")
	public ResponseEntity<?> getExamStatistic(){
		return statisticService.getStatistic();
	}
}
