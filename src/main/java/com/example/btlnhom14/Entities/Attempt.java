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

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "Attempt")
public class Attempt {

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
    @JsonIgnore
    @JoinColumn(name = "id", insertable = false, updatable=false)
    private User user;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "examId", insertable = false, updatable=false)
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