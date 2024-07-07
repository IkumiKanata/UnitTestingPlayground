package com.luv2code.springmvc;

import com.luv2code.springmvc.models.CollegeStudent;
import com.luv2code.springmvc.service.StudentAndGradeService;
import com.luv2code.springmvc.service.StudentDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@TestPropertySource("/application.properties")
public class StudentAndGradeServiceTest {

    @Autowired
    ApplicationContext context;


    @Autowired
    private StudentAndGradeService studentAndGradeService;

    @Autowired
    private StudentDao studentDao;

    @Test
    public void createStudentService() {
        studentAndGradeService.createStudent("John", "Doe", "test.com");

        CollegeStudent student = studentDao.findByEmailAddress("test.com");

        assertEquals("test.com", student.getEmailAddress());

    }

}
