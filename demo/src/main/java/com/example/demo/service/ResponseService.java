package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Question;
import com.example.demo.model.Response;
import com.example.demo.repository.ResponseRepository;

@Service
public class ResponseService {
    @Autowired
    private ResponseRepository responseRepository;

    @Autowired
    private QuestionService questionService;

    public Response findById(Integer id){
        Optional<Response> response = responseRepository.findById(id);
        if(!response.isEmpty()){
            return response.get();
        }
        return null;
    }

    public Response findByIdPersonalize(Integer idQuiz){
        Response response = responseRepository.findByidQuestionIdQuizId(idQuiz).get(0);
        return response;
    }

    public List<Response> findAllResponse(){
        Iterable<Response> iterable = responseRepository.findAll();
        List<Response> responses = new ArrayList<>();

        for(Response r : iterable){
            responses.add(r);
        }
        return responses;
    }

    public List<Response> findAllResponseById(Integer quizId){
        List<Response> responses = responseRepository.findByidQuestionIdQuizId(quizId);
        List<Response> responsesOut = new ArrayList<>();

        System.out.println(responses);
        for(Response r : responses){
            responsesOut.add(new Response(r.getId(), r.getDescription(), null));
        }
        return responsesOut;
    }

    public Response create(Integer idQuestion, Response response){
        Question question = questionService.findById(idQuestion);
        if(question != null){
            response.setIdQuestion(question);
            return responseRepository.save(response);
        }
        return null;
    }

    public void udpateByPut(Response response, Integer id){
        Response responseAct = findById(id);
        if(responseAct != null){
            String description = response.getDescription();
            Question idQuestion = response.getIdQuestion();

            responseAct.setDescription(description);
            responseAct.setIdQuestion(idQuestion);
            responseRepository.save(responseAct);
        }
    }

    public void updateByPatch(Response response, Integer id){
        Response responseAct = findById(id);
        if(responseAct != null){
            String description = response.getDescription();
            Question question = response.getIdQuestion();

            if(description != null && question == null){
                responseRepository.updateDescriptionById(description, id);

            }else if(description == null && question != null){
                responseRepository.updateQuestionById(question.getId(), id);

            }else if(description != null && question != null){
                responseAct.setDescription(description);
                responseAct.setIdQuestion(question);

                responseRepository.save(responseAct);
            }
        }
    }

    public void deleteById(Integer id){
        responseRepository.deleteById(id);
    }
}
