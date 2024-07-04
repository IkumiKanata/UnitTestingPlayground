package com.luv2code.test;

import com.luv2code.component.MvcTestingExampleApplication;
import com.luv2code.component.models.CollegeStudent;
import com.luv2code.component.models.StudentGrades;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = MvcTestingExampleApplication.class)
class ApplicationExampleTest {

    private int count;

    @Value("${info.app.name}")
    private String appName;

    @Value("${info.app.version}")
    private String appVersion;

    @Value("${info.app.description}")
    private String appDescription;

    @Value("${info.school.name}")
    private String schoolName;

    @Autowired
    CollegeStudent collegeStudent;

    @Autowired
    StudentGrades studentGrades;

    @Autowired
    ApplicationContext context;

    @BeforeEach
    public void setUp() {
        count = 1;
        System.out.println("Testing:" + appName + " which is " + appDescription + " Version:" + appVersion
                + ".Execution of test method " + count);

        collegeStudent.setFirstname("John");
        collegeStudent.setLastname("Doe");
        collegeStudent.setEmailAddress("test.com");
        studentGrades.setMathGradeResults(new ArrayList<>(List.of(90.0, 80.0, 70.0)));
        collegeStudent.setStudentGrades(studentGrades);
    }

    @Test
    @DisplayName("add grade results for student grades")
    void AddGradeResultsForStudentGrades() {
        assertEquals(240, studentGrades.addGradeResultsForSingleClass(collegeStudent.getStudentGrades().getMathGradeResults()));
    }

    @DisplayName("add grade results for student grades not equal")
    @Test
    void AddGradeResultsForStudentGradesNotEqual() {
        assertNotEquals(250, studentGrades.addGradeResultsForSingleClass(collegeStudent.getStudentGrades().getMathGradeResults()));
    }

    @Test
    @DisplayName("GradeGreater false")
    void isGradeGraterFalse() {
        assertFalse(studentGrades.isGradeGreater(90.0, 100000), "failure - should be false");
    }

    @Test
    @DisplayName("create student without grades")
    void createStudentWithoutGrades() {
        var student = context.getBean("collegeStudent", CollegeStudent.class);
        student.setFirstname("John");
        student.setLastname("Doe");
        student.setEmailAddress("test.com");
        assertNotNull(student.getFirstname());
        assertNotNull(student.getLastname());
        assertNotNull(student.getEmailAddress());
        assertNull(student.getStudentGrades());
    }

}
