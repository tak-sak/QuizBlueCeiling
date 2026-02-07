package com.example.demo.controller;

import com.example.demo.entity.Quiz;
import com.example.demo.service.QuizService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class QuizController {

    @Autowired
    private QuizService quizService;

    @GetMapping("/")
    public String opening() {
        return "opening";
    }

    @GetMapping("/start")
    public String start(HttpSession session) {
        session.setAttribute("score", 0);
        // ServiceにシャッフルされたIDリストを作ってもらう
        List<Integer> quizIds = quizService.getShuffledQuizIds();
        session.setAttribute("quizIds", quizIds);
        return "redirect:/quiz?index=0";
    }

    @GetMapping("/quiz")
    public String quiz(@RequestParam(defaultValue = "0") Integer index, HttpSession session, Model model) {
        List<Integer> quizIds = (List<Integer>) session.getAttribute("quizIds");

        if (quizIds == null || index >= quizIds.size()) {
            return "redirect:/result";
        }

        Quiz quiz = quizService.getQuizById(quizIds.get(index));
        model.addAttribute("quiz", quiz);
        model.addAttribute("currentIndex", index);
        return "index";
    }

    @PostMapping("/answer")
    @ResponseBody
    public Map<String, Object> answer(@RequestParam Integer id, @RequestParam Integer choice,
                                      @RequestParam Integer currentIndex, HttpSession session) {
        Map<String, Object> response = new HashMap<>();

        // 正解判定をServiceに依頼
        boolean isCorrect = quizService.checkAnswer(id, choice);
        response.put("correct", isCorrect);

        if (isCorrect) {
            Integer score = (Integer) session.getAttribute("score");
            session.setAttribute("score", (score != null ? score : 0) + 1);
            response.put("nextIndex", currentIndex + 1);
        } else {
            response.put("nextIndex", -1); // 不正解なら即終了
        }

        return response;
    }

    @GetMapping("/result")
    public String result(HttpSession session, Model model) {
        Integer score = (Integer) session.getAttribute("score");
        model.addAttribute("score", (score != null) ? score : 0);
        return "result";
    }
}