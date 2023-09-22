package com.jpa.jpacollection;

import com.jpa.jpacollection.entity.Student;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.sql.SQLOutput;
import java.util.List;

@SpringBootTest
class JpaCollectionApplicationTests {

    @PersistenceContext
    EntityManager em;

    @Transactional
    @Test
    public void testStudent() {
        Student student = new Student("Pilseong", "Heo", "heops79@gmial.com");
        List<String> course = student.getCourse();

        course.add("Math");
        course.add("Science");
        course.add("Economics");
        course.add("Language");
        course.add("Language");

        //insert함
        em.persist(student);

//        TypedQuery<Student> query = em.createQuery("SELECT m FROM Student m", Student.class);
//
//        List<Student> resultList = query.getResultList();
//        for(Student member : resultList) {
//            System.out.println("member = " + member);
//        }

        Student st1 = em.find(Student.class, 1L); //1L 대신 student.getId() 사용 =>
        for(String corse: st1.getCourse()) {
            System.out.println(corse);
        }
    }

    @Test
    void contextLoads() {
    }

}
