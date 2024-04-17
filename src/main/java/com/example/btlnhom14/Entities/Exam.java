package com.example.btlnhom14.Entities;
import java.sql.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
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
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "Exam")
public class Exam {

    // Bảng liệt kê các bài thi.
    // GenerationType.IDENTITY là id tự động tăng
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long examId;

    // Tên bài thi
    private String examName;

    // Mô tả
    private String description;

    // Loại kì thi vd: cuối kì, giữa kì
    private String examType;

//    @Temporal(TemporalType.TIMESTAMP)
    // Thời gian bắt đầu
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss", timezone = "UTC+7")
    private Date start;

//    @Temporal(TemporalType.TIMESTAMP)
    // Thời gian kết thúc
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss", timezone = "UTC+7")
    private Date end;

    // Xác định mối quan hệ 1-n hoặc 1-n với các bảng tương ứng 
    @OneToMany(mappedBy = "exam", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Result> Results;

    @OneToMany(mappedBy = "exam", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Attempt> Attempts;

    @JsonIgnore
    @OneToMany(mappedBy = "exam", cascade = CascadeType.ALL)
    private List<Question> questions;

    @JsonIgnore
    @OneToOne(mappedBy = "exam", cascade = CascadeType.ALL)
    private Statistic Statistic;

    @JsonIgnore
    @OneToMany(mappedBy = "exam", cascade = CascadeType.ALL)
    private List<UserAnswer> userAnswers;
}
