package com.example.btlnhom14.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "Result")
public class Result {

    // Kết quả kì thi
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long resultId;

    // Điểm
    private int mark;

    // Trạng thái vd: đã hoàn thành, chưa hoàn thành...
    private String status;

    // Xác định mối quan hệ n-1 với user. Set userId là khóa ngoại của result
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "userId", insertable = false, updatable=false)
    private User user;

    //  Xác định mối quan hệ n-1 với exam. Set examId là khóa ngoại của result
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "examId", insertable = false, updatable=false)
    private Exam exam;

    //Khóa ngoại
    private Long userId;

    //Khóa ngoại
    private Long examId;

    //Constructor
    public Result(Long userId, Long examId, int score, String status) {
        this.mark = mark;
        this.status = status;
        this.userId = userId;
        this.examId = examId;
    }
}
