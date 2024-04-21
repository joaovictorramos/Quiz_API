package com.example.demo.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Question;
import com.example.demo.model.Quiz;
import com.example.demo.model.Response;
import com.example.demo.repository.QuizRepository;

@Service
public class QuizService {
    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private ResponseService responseService;

    public Quiz findById(Integer id){
        Optional<Quiz> quiz = quizRepository.findById(id);
        if(!quiz.isEmpty()){
            return quiz.get();
        }
        return null;
    }

    public List<Quiz> findAllQuiz(){
        Iterable<Quiz> iterable = quizRepository.findAll();
        List<Quiz> questions = new ArrayList<>();

        for(Quiz q : iterable){
            questions.add(q);
        }
        return questions;
    }

    public List<Response> findAllResponse(){
        return responseService.findAllResponse();
    }

    public List<Response> findAllResponseById(Integer quizId){
        return responseService.findAllResponseById(quizId);
    }

    public Quiz create(Quiz quiz){
        quiz.setDateCreate(new Date());
        Quiz quizOut = quizRepository.save(quiz);
        return quizOut;
    }

    public Response createAll(Response response){
        Question question = response.getIdQuestion();
        Quiz quiz = question.getIdQuiz();
        quiz.setDateCreate(new Date());

        Quiz quizOut = quizRepository.save(quiz);
        Integer idQuiz = quizOut.getId();

        questionService.create(idQuiz, question);
        responseService.create(idQuiz, response);
        return response;
    }

    public Response createResponseByIdQuiz(Integer id, Response response){
        String correctDescription = response.getIdQuestion().getDescription(); // corrigir ilustração da descrição
        Question question = response.getIdQuestion();

        questionService.create(id, question);
        responseService.create(id, response);
        response.getIdQuestion().setDescription(correctDescription); // corrigir ilustração da descrição
        return response;
    }

    public Response updateByPut(Quiz quiz, Integer id){
        Quiz quizAct = quizRepository.findById(id).get();
        if(quizAct != null){
            String name = quiz.getName();
            String description = quiz.getDescription();
            Date date = new Date();

            quizAct.setName(name);
            quizAct.setDescription(description);
            quizAct.setDateCreate(date);

            Quiz quizOut = quizRepository.save(quizAct);
            Response responseForUpdate = responseService.findByIdPersonalize(quizOut.getId());
            return responseForUpdate;
        }
        return null;
    }

    public Response updateByPatch(Quiz quiz, Integer id){
        Optional<Quiz> quizAct = quizRepository.findById(id);
        if(!quizAct.isEmpty()){
            String name = quiz.getName();
            String description = quiz.getDescription();
            Date date = new Date();

            if(name != null && description == null){
                quizRepository.updateNameById(date, name, id);

                Response responseOut = responseService.findByIdPersonalize(id);
                return responseOut;

            }else if(name == null && description != null){
                quizRepository.updateDescriptionById(date, description, id);

                Response responseOut = responseService.findByIdPersonalize(id);
                return responseOut;

            }else if(name != null && description != null){
                quizRepository.updateById(date, name, description, id);

                Response responseOut = responseService.findByIdPersonalize(id);
                return responseOut;
            }
        }
        return null;
    }

    public void deleteById(Integer id){
        quizRepository.deleteById(id);
    }
}
