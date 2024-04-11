package com.example.demo_spring_redis.controller;

import com.example.demo_spring_redis.entity.Student;
import com.example.demo_spring_redis.service.StudentRedisService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/student")
public class StudentRedisController {

    private final StudentRedisService studentRedisService;

    @GetMapping
    public ResponseEntity<List<Student>> findAll() {
        return new ResponseEntity<>(studentRedisService.findAll(), HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<Student> save(@RequestBody Student student) {
        return new ResponseEntity<>(studentRedisService.save(student), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> findById(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(studentRedisService.findById(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(studentRedisService.deleteById(id), HttpStatus.OK);
    }
}
