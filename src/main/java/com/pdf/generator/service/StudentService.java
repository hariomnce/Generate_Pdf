package com.pdf.generator.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pdf.generator.entity.Student;
import com.pdf.generator.repository.StudentRepository;

@Service
public class StudentService {

	@Autowired
	StudentRepository studentRepository;

	public void save(Student student) {
		studentRepository.save(student);
	}

	public List<Student> findAllStudents() {
		return studentRepository.findAll();
	}

}
