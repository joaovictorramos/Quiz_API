package com.example.demo.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Question;
import com.example.demo.record.QuestionRecord;
import com.example.demo.service.QuestionService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/")
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    @GetMapping("/pergunta/{id}")
    public ResponseEntity<Question> findById(@PathVariable("id")Integer id){
        Question question = questionService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(question);
    }

    @PostMapping("/{idQuiz}/pergunta")
    public ResponseEntity<Question> create(@PathVariable("idQuiz")Integer idQuiz, @RequestBody @Valid QuestionRecord questionRecord){
        var question = new Question();
        BeanUtils.copyProperties(questionRecord, question);
        Question questionOut = questionService.create(idQuiz, question);
        return ResponseEntity.status(HttpStatus.CREATED).body(questionOut);
    }

    @PutMapping("/pergunta/{id}")
    public ResponseEntity<String> updateByPut(@RequestBody @Valid QuestionRecord questionRecord, @PathVariable("id")Integer id){
        var question = new Question();
        BeanUtils.copyProperties(questionRecord, question);
        questionService.updateByPut(question, id);
        return ResponseEntity.status(HttpStatus.OK).body("Question updated");
    }

    @PatchMapping("/pergunta/{id}")
    public ResponseEntity<String> updateByPatch(@RequestBody @Valid QuestionRecord questionRecord, @PathVariable("id")Integer id){
        var question = new Question();
        BeanUtils.copyProperties(questionRecord, question);
        questionService.updateByPatch(question, id);
        return ResponseEntity.status(HttpStatus.OK).body("Question updated");
    }

    @DeleteMapping("/pergunta/{id}")
    public ResponseEntity<String> deleteById(@PathVariable("id")Integer id){
        questionService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Question deleted");
    }
}
