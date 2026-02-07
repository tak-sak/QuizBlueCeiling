package com.example.demo.repository;

import com.example.demo.entity.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Integer> {
    // 現在のIDより大きいIDを持つクイズを、昇順で1件だけ取得する
    Optional<Quiz> findFirstByIdGreaterThanOrderByIdAsc(Integer id);
}