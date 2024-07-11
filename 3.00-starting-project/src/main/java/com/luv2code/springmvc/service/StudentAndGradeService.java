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

    public void createStudent(String firstname, String lastname, String emailAddress) {
        CollegeStudent student = new CollegeStudent(firstname, lastname, emailAddress);
        student.setId(0);
        studentDao.save(student);
    }

    public boolean isStudentNullCheck(int id) {
        return studentDao.findById(id).isPresent();
    }

    public void deleteStudent(int id) {
        studentDao.deleteById(id);
    }

    public Iterable<CollegeStudent> getGradeBook() {
        return studentDao.findAll();
    }
}
