package com.example.btlnhom14.Entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "Question")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Question {

    // Bảng liệt kê các câu hỏi 
    //  GenerationType.IDENTITY
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long questionId;

    // Nội dung câu hỏi
    private String questionText;

    // Đáp án A
    private String OptA;
    // Đáp án B
    private String OptB;
    // Đáp án C
    private String OptC;
    // Đáp án D
    private String OptD;

    // Câu trả lời
    private String Answer;

    // Khóa ngoại ánh xạ với joinColum bảng exam ở bên dưới
    private Long examId;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "examId", insertable = false, updatable=false)
    private Exam exam;

    // Cái này là set QuestionId làm khóa ngoại của bảng userAnswer
    @OneToMany(cascade = CascadeType.ALL)
    @JsonIgnore
    @JoinColumn(name = "questionId", insertable = false, updatable=false)
    private List<UserAnswer> userAnswers;
}
