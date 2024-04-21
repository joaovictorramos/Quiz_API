package com.example.demo.repository;

import java.util.Date;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.demo.model.Quiz;

import jakarta.transaction.Transactional;

public interface QuizRepository extends CrudRepository<Quiz, Integer>{
    @Modifying
    @Transactional
    @Query(value="""
            UPDATE quiz
            SET date_create = :date, name = :name
            WHERE id = :id
            """, nativeQuery=true)
    void updateNameById(@Param("date")Date date, @Param("name")String name, @Param("id")Integer id);

    @Modifying
    @Transactional
    @Query(value="""
            UPDATE quiz
            SET date_create = :date, description = :description
            WHERE id = :id
            """, nativeQuery=true)
    void updateDescriptionById(@Param("date")Date date, @Param("description")String description, @Param("id")Integer id);

    @Modifying
    @Transactional
    @Query(value="""
            UPDATE quiz
            SET date_create = :date, name = :name, description = :description
            WHERE id = :id
            """, nativeQuery=true)
    void updateById(@Param("date")Date date, @Param("name")String name, @Param("description")String description, @Param("id")Integer id);
}
