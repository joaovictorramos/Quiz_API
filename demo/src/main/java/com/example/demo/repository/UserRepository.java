package com.example.demo.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.demo.model.User;

import jakarta.transaction.Transactional;

public interface UserRepository extends CrudRepository<User, Integer>{
    @Modifying
    @Transactional
    @Query(value = """
            UPDATE user_entity
            SET login = :login
            WHERE id = :id
            """, nativeQuery = true)
    void updateLoginById(@Param("login")String login, @Param("id")Integer id);

    @Modifying
    @Transactional
    @Query(value = """
            UPDATE user_entity
            SET password = :password
            WHERE id = :id
            """, nativeQuery = true)
    void updatePasswordById(@Param("password")String password, @Param("id")Integer id);

    @Modifying
    @Transactional
    @Query(value = """
            UPDATE user_entity
            SET login = :login, password = :password
            WHERE id = :id
            """, nativeQuery = true)
    void updateById(@Param("login")String login, @Param("password")String password, @Param("id")Integer id);
}
