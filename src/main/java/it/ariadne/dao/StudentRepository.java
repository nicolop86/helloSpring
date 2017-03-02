package it.ariadne.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import it.ariadne.model.Student;

public interface StudentRepository extends CrudRepository<Student, Integer>{

	public List<Student> findBySurname(String surname);
	public List<Student> findByName(String name);
	public List<Student> findById(String id);
	public List<Student> findByNameAndSurname(String name, String surname);
	
	@Query("select s from Student s join s.courseList c where c.title = :title")
	/*Use object's fields as name*/
	public List<Student> findByCourse(@Param("title") String title);
}