package com.luv2code.test;


import com.luv2code.component.MvcTestingExampleApplication;
import com.luv2code.component.models.CollegeStudent;
import com.luv2code.component.models.StudentGrades;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = MvcTestingExampleApplication.class)
public class ReflectionTestUtilsTest {

    @Autowired
    ApplicationContext context;

    @Autowired
    CollegeStudent collegeStudent;

    @Autowired
    StudentGrades studentGrades;

    @BeforeEach
    public void setUp() {
        collegeStudent.setFirstname("John");
        collegeStudent.setLastname("Doe");
        collegeStudent.setEmailAddress("test.com");
        collegeStudent.setStudentGrades(studentGrades);

        ReflectionTestUtils.setField(collegeStudent, "id", 1);
        ReflectionTestUtils.setField(collegeStudent, "studentGrades", new StudentGrades(List.of(90.0, 80.0, 70.0)));
    }

    @Test
    void getPrivateField() {
        assertEquals(1, ReflectionTestUtils.getField(collegeStudent, "id"));
    }

    @Test
    void getPrivateMethod() {
        assertEquals("John 1", ReflectionTestUtils.invokeMethod(collegeStudent, "getFirstnameAndId"));
    }


}
