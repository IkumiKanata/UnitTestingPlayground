package com.luv2code.springmvc.service;


import com.luv2code.springmvc.models.CollegeStudent;
import com.luv2code.springmvc.models.MathGrade;
import com.luv2code.springmvc.repository.MathGradesDao;
import com.luv2code.springmvc.repository.StudentDao;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class StudentAndGradeService {

    private final StudentDao studentDao;

    private final MathGradesDao mathGradesDao;

    @Qualifier("mathGrades")
    private final MathGrade mathGrade;


    StudentAndGradeService(StudentDao studentDao, MathGradesDao mathGradesDao, MathGrade mathGrade) {
        this.studentDao = studentDao;
        this.mathGradesDao = mathGradesDao;
        this.mathGrade = mathGrade;
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

    public boolean createMathGrade( double grade, int studentId, String gradeType) {
        if(!isStudentNullCheck(studentId)) {
            return false;
        }

        if(grade >= 0 && grade <= 100) {
            if(gradeType.equals("math")) {
                mathGrade.setId(0);
                mathGrade.setGrade(grade);
                mathGrade.setStudentId(studentId);
                mathGradesDao.save(mathGrade);
                return true;
            }
        }
        return false;
    }
}
