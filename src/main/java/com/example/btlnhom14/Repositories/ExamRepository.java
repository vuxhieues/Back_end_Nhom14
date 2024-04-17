package com.example.btlnhom14.Repositories;

import java.util.List;

import com.example.btlnhom14.Entities.Exam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface ExamRepository extends JpaRepository<Exam, Long>{
    List<Exam> findByExamName(String examName);

    List<Exam> findByExamType(String examType);

}
