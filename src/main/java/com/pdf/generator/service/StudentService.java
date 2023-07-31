package com.pdf.generator.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.pdf.generator.entity.Student;
import com.pdf.generator.repository.StudentRepository;

@Service
public class StudentService {

	@Autowired
	StudentRepository studentRepository;

//	Save students
	public void save(Student student) {
		studentRepository.save(student);
	}

//	Get all students
	public List<Student> findAllStudents() {
		return studentRepository.findAll();
	}

//	Get all students with pagination & sorting
	public List<Student> getAllStudents(Integer pageNumber, Integer pageSize, String sortBy) {
		Pageable page = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy));
		Page<Student> pagedResult = studentRepository.findAll(page);
		if (pagedResult.hasContent()) {
			return pagedResult.getContent();
		} else {
			return new ArrayList<Student>();
		}
	}

}
