package com.jpa.jpacollection.repository;

import com.jpa.jpacollection.entity.Student;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.lang.reflect.Type;
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
    public void update(Student student) {
        //기존에 있는 자료만 merge => PK 있어야 함
        //update
        em.merge(student);

    }


    //insert나 update => @Transactional
    @Transactional
    public Student findById(Long id) {
        Student student = em.find(Student.class , id);
        return student;
    }

    @Transactional
    public void insertNewCourse(Long id, String course) {
        //아이디, 과목명을 받아 영속성을 받게 함.
        //Student 안에 course 있음 (물리적으로는 다른 테이블에 있음)
        Student student = em.find(Student.class, id);
        List<String> courses = student.getCourse();
        courses.add(course);
    }

    @Transactional
    public void deleteById(Long id){
        Student student = em.find(Student.class, id);
        em.remove(student);
    }

    public List<Student> findAll() {
        String sql = "select m from Student m";
        TypedQuery<Student> query = em.createQuery(sql, Student.class);

        List<Student> list = query.getResultList();
        return list;
    }
}
