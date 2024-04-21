package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> findAllUser(){
        Iterable<User> iterable = userRepository.findAll();
        List<User> users = new ArrayList<>();

        for(User u : iterable){
            users.add(u);
        }
        return users;
    }

    public User create(User user){
        User userOut = userRepository.save(user);
        return userOut;
    }

    public User updateByPut(User user, Integer id){
        User userAct = userRepository.findById(id).get();
        if(userAct != null){
            String login = user.getLogin();
            String password = user.getPassword();

            userAct.setLogin(login);
            userAct.setPassword(password);

            User userOut = userRepository.save(userAct);
            return userOut;
        }
        return null;
    }

    public void updateByPatch(User user, Integer id){
        User userAct = userRepository.findById(id).get();
        if(userAct != null){
            String login = user.getLogin();
            String password = user.getPassword();

            if(login != null && password == null){
                userRepository.updateLoginById(login, id);

            }else if(login == null && password != null){
                userRepository.updatePasswordById(password, id);

            }else if(login != null && password != null){
                userRepository.updateById(login, password, id);
            }
        }
    }

    public void deleteById(Integer id){
        Optional<User> user = userRepository.findById(id);
        if(!user.isEmpty()){
            userRepository.delete(user.get());
        }
    }
}
