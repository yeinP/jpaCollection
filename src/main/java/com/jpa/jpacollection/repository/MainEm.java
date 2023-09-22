package com.jpa.jpacollection.repository;

import com.jpa.jpacollection.entity.Student;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class MainEm {
    @PersistenceContext
    EntityManager em;

    @Transactional
    public  Long insert(Student student) {
        em.persist(student);
        return student.getId();
    }

    @Transactional
    public void update(Student student) { em.merge(student);}

    @Transactional
    public Student findById(Long id) {
        Student student = em.find(Student.class , id);
        return student;
    }

    @Transactional
    public void insertNewCourse(Long id, String course) {
        Student student = em.find(Student.class, id);
        List<String> courses = student.getCourse();
        courses.add(course);
    }

    @Transactional
    public void deleteById(Long id){
        Student student = em.find(Student.class, id);
        em.remove(student);
    }
}
