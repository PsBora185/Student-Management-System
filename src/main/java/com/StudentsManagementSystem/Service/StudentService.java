package com.StudentsManagementSystem.Service;

import java.util.List;
import com.StudentsManagementSystem.Entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface StudentService {

    public List<Student> getAllStudents();

    public Student saveStudent(Student student);

    public Student getById(int id);

    public void deleteById(int id);

    Page<Student> getAllStudents(Pageable pageable);

    Page<Student> searchStudents(String keyword, Pageable pageable);

    long countAllStudents();


}
