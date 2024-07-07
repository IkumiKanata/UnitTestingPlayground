package com.luv2code.springmvc.service;


import com.luv2code.springmvc.models.CollegeStudent;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class StudentAndGradeService {

    private final StudentDao studentDao;

    StudentAndGradeService(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    public void createStudent(String firstName, String lastName, String emailAddress) {
        CollegeStudent student = new CollegeStudent(firstName, lastName, emailAddress);
        student.setId(0);
        studentDao.save(student);
    }
}
