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

import com.example.demo.model.User;
import com.example.demo.record.UserRecord;
import com.example.demo.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/")
public class UserController {
    @Autowired
    UserService userService = new UserService();

    @GetMapping("/usuario")
    public ResponseEntity<List<User>> findAllUser(){
        List<User> users = userService.findAllUser();
        return ResponseEntity.status(HttpStatus.OK).body(users);
    }

    @PostMapping("/usuario")
    public ResponseEntity<User> create(@RequestBody @Valid UserRecord userRecord){
        var user = new User();
        BeanUtils.copyProperties(userRecord, user);
        User userOut = userService.create(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(userOut);
    }

    @PutMapping("/usuario/{id}")
    public ResponseEntity<User> updateByPut(@RequestBody @Valid UserRecord userRecord, @PathVariable("id")Integer id){
        var user = new User();
        BeanUtils.copyProperties(userRecord, user);
        User userOut = userService.updateByPut(user, id);
        return ResponseEntity.status(HttpStatus.OK).body(userOut);
    }

    @PatchMapping("/usuario/{id}")
    public ResponseEntity<String> updateByPatch(@RequestBody @Valid UserRecord userRecord, @PathVariable("id")Integer id){
        var user = new User();
        BeanUtils.copyProperties(userRecord, user);
        userService.updateByPatch(user, id);
        return ResponseEntity.status(HttpStatus.OK).body("User updated");
    }

    @DeleteMapping("/usuario/{id}")
    public ResponseEntity<String> deleteById(@PathVariable("id")Integer id){
        userService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body("User deleted");
    }
}
