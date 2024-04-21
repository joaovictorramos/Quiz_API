package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Question;
import com.example.demo.model.Quiz;
import com.example.demo.repository.QuestionRepository;

@Service
public class QuestionService {
    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private QuizService quizService;

    public Question findById(Integer id){
        Optional<Question> question = questionRepository.findById(id);
        if(!question.isEmpty()){
            return question.get();
        }
        return null;
    }

    public Question create(Integer idQuiz, Question question){
        Quiz quiz = quizService.findById(idQuiz);
        if(quiz != null){
            question.setIdQuiz(quiz);
            return questionRepository.save(question);
        }
        return null;
    }

    public void updateByPut(Question question, Integer id){
        Question questionAct = findById(id);
        if(questionAct != null){
            String description = question.getDescription();

            questionAct.setDescription(description);
            questionRepository.save(questionAct);
        }
    }

    public void updateByPatch(Question question, Integer id){
        Question questionAct = findById(id);
        if(questionAct != null){
            String description = question.getDescription();

            if(description != null){
                questionAct.setDescription(description);
                questionRepository.updateById(description, id);
            }
        }
    }

    public void deleteById(Integer id){
        questionRepository.deleteById(id);
    }
}
