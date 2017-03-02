package it.ariadne.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.ariadne.model.Course;

public interface CourseRepository extends CrudRepository<Course, Integer>{
	
	public List<Course> findByTeacherOrderByTeacherAsc(String teacher);
	public List<Course> findByCreditsGreaterThanOrderByCreditsDesc(Integer nCredits);
	
}