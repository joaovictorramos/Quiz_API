package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.demo.model.Response;

import jakarta.transaction.Transactional;

public interface ResponseRepository extends CrudRepository<Response, Integer>{
    @Modifying
    @Transactional
    @Query(value="""
            UPDATE response
            SET description = :description
            WHERE id = :id
            """, nativeQuery = true)
    void updateDescriptionById(@Param("description")String description, @Param("id")Integer id);

    @Modifying
    @Transactional
    @Query(value="""
            UPDATE response
            SET id_question = :idQuestion
            WHERE id = :id
            """, nativeQuery = true)
    void updateQuestionById(@Param("idQuestion")Integer idQuestion, @Param("id")Integer id);

    List<Response> findByidQuestionIdQuizId(Integer idQuiz);
}
