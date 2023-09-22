package com.jpa.jpacollection.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="student")
@NoArgsConstructor
@Data
public class Student {

    public Student(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    @Column(name="first_name")
    private  String firstName;

    @Column(name="last_name")
    private String lastName;

    @Column(name="email")
    private  String email;

    @ElementCollection
    @CollectionTable(
            name="course", //테이블명
            joinColumns = @JoinColumn(name="student_id") //외래키
    )

    //@OrderColumn
    //List 인터페이스에 @OrderColumn을 추가하면 순서가 있는 특수한 컬랙션으로 인식
    // -> DB에 순서 값을 저장해서 조회할 때 사용.
    @OrderBy("student_id desc, course_name desc")
    @Column(name="course_name") //컬럼명
    private List<String> course = new ArrayList<>();
    //클래스로 가능

}
