package com.example.btlnhom14.Entities;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// BẤT KÌ CLASS NÀO CÓ @Entity và @Table SẼ LIÊN QUAN ĐẾN CSDL !!!
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "Attempt")
public class Attempt {

    // Đây là bảng về thực hiện bài thi. Một bài thi có thể tham gia bởi nhiều người và một bài thi có thể được khởi tạo nhiều lần
    // Đây là id GenerationType.IDENTITY là tự động tăng 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long attemptId;

//    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss", timezone = "UTC+7")
    private Date start;

//    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss", timezone = "UTC+7")
    private Date end;

    @ManyToOne
    // Xác định mối quan hệ n-1 với user
    @JsonIgnore
    @JoinColumn(name = "id", insertable = false, updatable=false)
// JoinColumn là xác định khóa ngoại ánh xạ sang table user với khóa ngoại là userId
    private User user;

    @ManyToOne
// xác định mối quan hệ n-1 với exam
    @JsonIgnore
    @JoinColumn(name = "examId", insertable = false, updatable=false)
 // JoinColumn là xác định khóa ngoại ánh xạ sang table exam với khóa ngoại là examId
    private Exam exam;

    private Long userId;
    private Long examId;

    public Attempt(Long userId, Long examId, Date startTime, Date endTime) {
        this.start = start;
        this.end = end;
        this.userId = userId;
        this.examId = examId;
    }
}
