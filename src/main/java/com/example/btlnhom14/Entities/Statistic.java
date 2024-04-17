package com.example.btlnhom14.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@Table(name = "Statistic")
@NoArgsConstructor
@AllArgsConstructor
public class Statistic {

    // Thống kê của bài thi.
    // GenerationType.IDENTITY khóa tự động tăng
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long statisticId;

    //Tổng số người tham gia
    private int totalJoin;

    // % hoàn thành
    private double complete;

    // Điểm trung bình
    private double averageMark;

    // Tên bài thi
    private String examName;

    // Mối quan hệ 1-1. examId là khóa ngoại của Statistic
    @OneToOne
    @JoinColumn(name = "examId", insertable = false, updatable = false)
    @JsonIgnore
    private Exam exam;
}
