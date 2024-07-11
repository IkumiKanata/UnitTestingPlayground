package com.luv2code.springmvc.controller;

import com.luv2code.springmvc.models.*;
import com.luv2code.springmvc.service.StudentAndGradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class GradebookController {

    private final Gradebook gradebook;

	private final StudentAndGradeService studentAndGradeService;

	@Autowired
	public GradebookController(StudentAndGradeService studentAndGradeService, Gradebook gradebook) {
		this.studentAndGradeService = studentAndGradeService;
		this.gradebook = gradebook;
	}

	@GetMapping("/gradebook")
	public String getGradebook(Model m) {
		m.addAttribute("gradebook", gradebook);
		return "gradebook";
	}

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getStudents(Model m) {
		m.addAttribute("students", studentAndGradeService.getGradeBook());
        return "index";
    }

	@PostMapping("/")
	public String createStudent(@ModelAttribute("student") CollegeStudent student, Model m) {
		studentAndGradeService.createStudent(student.getFirstname(), student.getLastname(), student.getEmailAddress());
		return "index";
	}



    @GetMapping("/studentInformation/{id}")
    public String studentInformation(@PathVariable int id, Model m) {
        return "studentInformation";
    }



}
