package com.luv2code.springmvc;

import com.luv2code.springmvc.models.CollegeStudent;
import com.luv2code.springmvc.service.StudentAndGradeService;
import com.luv2code.springmvc.service.StudentDao;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource("/application.properties")
public class StudentAndGradeServiceTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private StudentAndGradeService studentAndGradeService;

    @Autowired
    private StudentDao studentDao;

    @BeforeEach
    public void setUp() {
        jdbcTemplate.execute("insert into student (id, firstname, lastname, email_address) values (1, 'Taco', 'Bell', 'test.com')");
    }


    @Test
    public void createStudentService() {
        studentAndGradeService.createStudent("John", "Doe", "test2.com");

        CollegeStudent student = studentDao.findByEmailAddress("test2.com");

        assertEquals("test2.com", student.getEmailAddress());

    }

    @Test
    public void isStudentNullCheck() {
        assertTrue( studentAndGradeService.isStudentNullCheck(1));
        assertFalse( studentAndGradeService.isStudentNullCheck(0));
    }

    @Test
    public void deleteStudentService() {
        Optional<CollegeStudent> student = studentDao.findById(1);
        assertTrue(student.isPresent());
        studentAndGradeService.deleteStudent(1);

        student = studentDao.findById(1);
        assertFalse(student.isPresent());
    }

    @Test
    @Sql({"/insertData.sql"})
    public void getGradeBookService() {
        Iterable<CollegeStudent> students = studentAndGradeService.getGradeBook();


        List<CollegeStudent> studentList = new ArrayList<>();

        for (CollegeStudent student : students) {
            studentList.add(student);
        }
        assertEquals(6, studentList.size());
    }

    @AfterEach
    public void tearDown() {
        jdbcTemplate.execute("delete from student");
    }

}
