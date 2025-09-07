package com.StudentsManagementSystem.ServiceImpl;

import com.StudentsManagementSystem.Entity.Student;
import com.StudentsManagementSystem.Service.StudentService;
import com.StudentsManagementSystem.Repository.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceImpl implements StudentService{

    @Autowired
    StudentRepo studentRepo;

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
        return studentRepo.findById(id).get();
    }

    @Override
    public void deleteById(int id) {
        studentRepo.deleteById(id);
    }
}
