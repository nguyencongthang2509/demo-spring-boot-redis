package com.example.demo_spring_redis.service;

import com.example.demo_spring_redis.entity.Student;
import com.example.demo_spring_redis.repository.StudentRedisRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentRedisService {

    private final StudentRedisRepository studentRedisRepository;

    public List<Student> findAll() {
        return studentRedisRepository.findAll();
    }

    public Student save(Student student) {
        return studentRedisRepository.save(student);
    }

    public Student findById(Integer id) {
        return studentRedisRepository.findStudentById(id);
    }

    public String deleteById(Integer id) {
        return studentRedisRepository.deleteStudent(id);
    }
}
