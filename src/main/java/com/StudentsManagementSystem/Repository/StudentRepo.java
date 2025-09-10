package com.StudentsManagementSystem.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.StudentsManagementSystem.Entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface StudentRepo extends JpaRepository<Student, Integer> {  // provides methods for data
    Page<Student> findByFirstNameOrLastNameOrEmail(
            String firstName, String lastName, String email, Pageable pageable);
}
