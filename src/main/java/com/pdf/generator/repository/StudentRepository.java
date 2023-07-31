package com.pdf.generator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.pdf.generator.entity.Student;

@EnableJpaRepositories
public interface StudentRepository extends JpaRepository<Student, Integer> {

	Student findByPhoneNumber(String phoneNumber);
}
