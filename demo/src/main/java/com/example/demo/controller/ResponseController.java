package com.example.demo.controller;

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

import com.example.demo.model.Response;
import com.example.demo.record.ResponseRecord;
import com.example.demo.service.ResponseService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/")
public class ResponseController {
    @Autowired
    private ResponseService responseService;

    @GetMapping("/resposta/{id}")
    public ResponseEntity<Response> findById(@PathVariable("id")Integer id){
        Response response = responseService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/{idQuestion}/resposta")
    public ResponseEntity<Response> create(@PathVariable("idQuestion")Integer idQuestion, @RequestBody @Valid ResponseRecord responseRecord){
        var response = new Response();
        BeanUtils.copyProperties(responseRecord, response);
        Response responseOut = responseService.create(idQuestion, response);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseOut);
    }

    @PutMapping("/resposta/{id}")
    public ResponseEntity<String> updateByPut(@RequestBody @Valid ResponseRecord responseRecord, @PathVariable("id")Integer id){
        var response = new Response();
        BeanUtils.copyProperties(responseRecord, response);
        responseService.udpateByPut(response, id);
        return ResponseEntity.status(HttpStatus.OK).body("Response updated");
    }

    @PatchMapping("/resposta/{id}")
    public ResponseEntity<String> updateByPatch(@RequestBody @Valid ResponseRecord responseRecord, @PathVariable("id")Integer id){
        var response = new Response();
        BeanUtils.copyProperties(responseRecord, response);
        responseService.updateByPatch(response, id);
        return ResponseEntity.status(HttpStatus.OK).body("Response updated");
    }

    @DeleteMapping("/resposta/{id}")
    public ResponseEntity<String> deleteById(@PathVariable("id")Integer id){
        responseService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Response deleted");
    }


}
