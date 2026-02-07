package com.example.demo.service;

import com.example.demo.entity.Quiz;
import com.example.demo.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuizService {

    @Autowired
    private QuizRepository quizRepository;

    /**
     * 全クイズのIDを取得し、ランダムに並び替えたリストを返す
     */
    public List<Integer> getShuffledQuizIds() {
        List<Integer> ids = quizRepository.findAll().stream()
                .map(Quiz::getId)
                .collect(Collectors.toList());
        Collections.shuffle(ids);
        return ids;
    }

    /**
     * 指定されたIDのクイズを取得する
     */
    public Quiz getQuizById(Integer id) {
        return quizRepository.findById(id).orElse(null);
    }

    /**
     * 正解判定を行う
     */
    public boolean checkAnswer(Integer id, Integer choice) {
        return quizRepository.findById(id)
                .map(quiz -> quiz.getAnswerId().equals(choice))
                .orElse(false);
    }
}