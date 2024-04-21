package com.example.demo.controller;

import java.util.List;

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

import com.example.demo.model.Quiz;
import com.example.demo.model.Response;
import com.example.demo.record.QuizRecord;
import com.example.demo.service.QuizService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/")
public class QuizController {
    @Autowired
    private QuizService quizService;

    /* ??? */
    @GetMapping("/quiz")
    public ResponseEntity<List<Quiz>> findAllQuiz(){
        List<Quiz> quizzes = quizService.findAllQuiz();
        return ResponseEntity.status(HttpStatus.OK).body(quizzes);
    }

    @GetMapping("/questionarios")
    public ResponseEntity<List<Response>> findAll(){
        List<Response> responses = quizService.findAllResponse();
        return ResponseEntity.status(HttpStatus.OK).body(responses);
    }

    @GetMapping("/questionario/{id}/respostas")
    public ResponseEntity<List<Response>> findAllResponseById(@PathVariable("id")Integer quizId){
        List<Response> responses = quizService.findAllResponseById(quizId);
        return ResponseEntity.status(HttpStatus.OK).body(responses);
    }

    /* ??? */
    @PostMapping("/quiz")
    public ResponseEntity<Quiz> create(@RequestBody @Valid QuizRecord quizRecord){
        var quiz = new Quiz();
        BeanUtils.copyProperties(quizRecord, quiz);
        Quiz quizOut = quizService.create(quiz);
        return ResponseEntity.status(HttpStatus.CREATED).body(quizOut);
    }

    @PostMapping("/questionarios")
    public ResponseEntity<Response> createAll(@RequestBody @Valid Response response){
        Response quizOut = quizService.createAll(response);
        return ResponseEntity.status(HttpStatus.CREATED).body(quizOut);
    }

    @PostMapping("/questionario/{id}/resposta")
    public ResponseEntity<Response> createResponses(@RequestBody @Valid Response response, @PathVariable("id")Integer idQuiz){
        Response responseOut = quizService.createResponseByIdQuiz(idQuiz, response);
        System.out.println(responseOut);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseOut);
    }

    @PutMapping("/questionarios/{id}")
    public ResponseEntity<Response> updateByPut(@RequestBody @Valid QuizRecord quizRecord, @PathVariable("id")Integer id){
        var quiz = new Quiz();
        BeanUtils.copyProperties(quizRecord, quiz);
        Response responseOut = quizService.updateByPut(quiz, id);
        return ResponseEntity.status(HttpStatus.OK).body(responseOut);
    }

    @PatchMapping("/questionarios/{id}")
    public ResponseEntity<Response> updateByPatch(@RequestBody @Valid QuizRecord quizRecord, @PathVariable("id")Integer id){
        var quiz = new Quiz();
        BeanUtils.copyProperties(quizRecord, quiz);
        Response responseOut = quizService.updateByPatch(quiz, id);
        return ResponseEntity.status(HttpStatus.OK).body(responseOut);
    }

    @DeleteMapping("/questionarios/{id}")
    public ResponseEntity<String> deleteById(@PathVariable("id")Integer id){
        quizService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Quiz deleted");
    }
}
