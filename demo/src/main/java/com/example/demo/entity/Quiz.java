package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "quizzes") // schema.sqlで作ったテーブル名
@Data
public class Quiz {

    @Id
    private Integer id;

    private String questionText;
    private String choice1;
    private String choice2;
    private String choice3;
    private String choice4;
    private Integer answerId;
    private String category;
    private Integer difficulty;
}