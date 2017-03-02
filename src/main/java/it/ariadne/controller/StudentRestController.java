package it.ariadne.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import it.ariadne.dao.CourseRepository;
import it.ariadne.dao.StudentRepository;
import it.ariadne.model.Course;
import it.ariadne.model.Student;

@RestController
@RequestMapping("/rest")
public class StudentRestController {

	@Autowired
	StudentRepository studentRepo;
	
	@Autowired
	CourseRepository courseRepo;

	@RequestMapping(value = "/findCorso/{title}", method = RequestMethod.GET, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public List<Student> getStudentByCourse(@PathVariable String title) {
		List<Student> studentList = studentRepo.findByCourse(title);
		return studentList;
	}

/*	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String getCoursePage(Model model) {
		return "coursesHome";
	}
	
	@RequestMapping(value = "/loadCourses",  method = RequestMethod.GET, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public List<Course> getCourses() {
		List<Course> courseList = (List<Course>) courseRepo.findAll();
		return courseList;
	}*/
	
}