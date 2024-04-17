package com.example.btlnhom14.Entities;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
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
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "UserAnswer")
public class UserAnswer {

// bảng liệt kê câu trả lời của USER
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userAnswerId;

    // Câu trả lời USER chọn
    private String selected;


    // Xác định quan hệ n-1 với user. Set userId trong bảng user là khóa ngoại
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "userId", insertable = false, updatable = false)
    private User user;

    // Xác định quan hệ n-1 với exam. Set examId trong bảng exam là khóa ngoại
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "examId", insertable = false, updatable = false)
    private Exam exam;

     // Xác định quan hệ n-1 với question. Set questionId trong bảng question là khóa ngoại
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "questionId", insertable = false, updatable = false)
    private Question question;

    // Các khóa ngoại xác định ở trên
    private Long userId;
    private Long examId;
    private Long questionId;

    //Constructor k liên quan đến csdl
    public UserAnswer(Long userId, Long examId, Long questionId, String selected) {
        this.selected = selected;
        this.userId = userId;
        this.examId = examId;
        this.questionId = questionId;
    }
}
