package com.example.demo_spring_redis.repository;

import com.example.demo_spring_redis.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class StudentRedisRepository {

    public static final String HASH_KEY = "Student";

    private final RedisTemplate<String, Object> template;

    @Autowired
    public StudentRedisRepository(RedisTemplate<String, Object> template) {
        this.template = template;
    }

    public Student save(Student student) {
        template.opsForHash().put(HASH_KEY, String.valueOf(student.getId()), student);
        return student;
    }

    public List<Student> findAll() {
        List<Object> students = template.opsForHash().values(HASH_KEY);
        return students.stream()
                .map(obj -> (Map<String, Object>) obj)
                .map(this::mapToStudent)
                .collect(Collectors.toList());
    }

    private Student mapToStudent(Map<String, Object> map) {
        Student student = new Student();
        student.setId((Integer) map.get("id"));
        student.setName((String) map.get("name"));
        student.setAge((Integer) map.get("age"));
        return student;
    }

    public Student findStudentById(int id) {
        Map<String, Object> studentMap = (Map<String, Object>) template.opsForHash().get(HASH_KEY, String.valueOf(id));
        return mapToStudent(studentMap);
    }

    public String deleteStudent(int id) {
        template.opsForHash().delete(HASH_KEY, String.valueOf(id));
        return "Student removed !!";
    }
}

