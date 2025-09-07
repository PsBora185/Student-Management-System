package com.StudentsManagementSystem.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.StudentsManagementSystem.Entity.Student;

public interface StudentRepo extends JpaRepository<Student, Integer> {  // provides methods for data

}
