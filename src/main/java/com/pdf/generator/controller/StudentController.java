package com.pdf.generator.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lowagie.text.DocumentException;
import com.pdf.generator.entity.Student;
import com.pdf.generator.service.StudentService;
import com.pdf.generator.util.PdfGenerator;

@RestController
//@RequestMapping("/slip")
public class StudentController {

	@Autowired
	StudentService studentService;

//	Save students
	@PostMapping("/save")
	public ResponseEntity<Student> saveStudent(@RequestBody Student student) {
		studentService.save(student);
		return new ResponseEntity<>(student, HttpStatus.CREATED);
	}

//	Edit by phone number
	@PutMapping("/updateStudentData")
	public ResponseEntity<Student> updateStudent(@RequestBody Student student) {
		studentService.save(student);
		return new ResponseEntity<>(student, HttpStatus.ACCEPTED);
	}

//	Get all students in pdf format	
	@GetMapping("/pdf/students")
	public void generatePdf(HttpServletResponse response) throws DocumentException, IOException {
		response.setContentType("application/pdf");
		DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-DD:HH:MM:SS");
		String currentDateTime = dateFormat.format(new Date());
		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=pdf_" + currentDateTime + ".pdf";
		response.setHeader(headerKey, headerValue);

		List<Student> studentList = studentService.findAllStudents();
		PdfGenerator generator = new PdfGenerator();
		generator.setStudentList(studentList);
		generator.generate(response);
	}

//	Get all students with pagination & sorting
	@GetMapping("/getPage")
	public ResponseEntity<List<Student>> getAllStudents(@RequestParam(defaultValue = "0") Integer pageNumber,
			@RequestParam(defaultValue = "10") Integer pageSize, @RequestParam(defaultValue = "id") String sortBy) {
		List<Student> list = studentService.getAllStudents(pageNumber, pageSize, sortBy);
		return new ResponseEntity<List<Student>>(list, new HttpHeaders(), HttpStatus.OK);
	}

}
