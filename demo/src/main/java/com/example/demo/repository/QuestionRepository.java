package com.example.demo.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.demo.model.Question;

import jakarta.transaction.Transactional;

public interface QuestionRepository extends CrudRepository<Question, Integer>{
    @Modifying
    @Transactional
    @Query(value="""
            UPDATE question
            SET description = :description
            WHERE id = :id
            """, nativeQuery = true)
    void updateById(@Param("description")String description, @Param("id")Integer id);
}
