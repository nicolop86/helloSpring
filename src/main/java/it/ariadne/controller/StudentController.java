package it.ariadne.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.ariadne.dao.StudentRepository;

@Controller
@RequestMapping("/students")
public class StudentController {

	@Autowired
	private StudentRepository studentRepo;
	
	@RequestMapping(value="/find/{surname}",  method = RequestMethod.GET)
	/*@PathVariable allows to tell that the method has to search for surname coming from URL*/
	public String getStudentBySurname(@PathVariable String surname, Model model) {
		model.addAttribute(studentRepo.findBySurname(surname));
		return "students";
	}
	
	@RequestMapping(value="/findByName/{name}",  method = RequestMethod.GET)
	/*@PathVariable allows to tell that the method has to search for name coming from URL*/
	public String getStudentByName(@PathVariable String name, Model model) {
		model.addAttribute(studentRepo.findByName(name));
		return "students";
	}
	
	@RequestMapping(value="/findByNameAndSurname/{name}/{surname}", method=RequestMethod.GET)
	public String getStudentByNameAndSurname(@PathVariable String name, @PathVariable String surname,
			Model model){
		model.addAttribute(studentRepo.findByNameAndSurname(name, surname));
		return "students";
	}
	
	@RequestMapping(value="/findCorso/{title}", method=RequestMethod.GET)
	public String getStudentByCourse(@PathVariable String title, Model model) {
		model.addAttribute(studentRepo.findByCourse(title));
		return "students";
	}
}
