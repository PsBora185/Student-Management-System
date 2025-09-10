package com.StudentsManagementSystem.ServiceImpl;

import com.StudentsManagementSystem.Entity.Student;
import com.StudentsManagementSystem.Service.StudentService;
import com.StudentsManagementSystem.Repository.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ServiceImpl implements StudentService{

    @Autowired
    private final StudentRepo studentRepo;

    public ServiceImpl(StudentRepo studentRepository) {
        this.studentRepo = studentRepository;
    }


    @Override
    public List<Student> getAllStudents() {
        return studentRepo.findAll();
    }

    @Override
    public Student saveStudent(Student student) {
        return studentRepo.save(student);
    }

    @Override
    public Student getById(int id) {
        return studentRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + id));
    }


    @Override
    public void deleteById(int id) {
        studentRepo.deleteById(id);
    }

    @Override
    public Page<Student> getAllStudents(Pageable pageable) {
        return studentRepo.findAll(pageable);
    }

    @Override
    public Page<Student> searchStudents(String keyword, Pageable pageable) {
        return studentRepo.findByFirstNameOrLastNameOrEmail(
                keyword, keyword, keyword, pageable);
    }

    @Override
    public long countAllStudents() {
        return studentRepo.count();
    }

}
