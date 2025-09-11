package com.StudentsManagementSystem.Controller;

import com.StudentsManagementSystem.Entity.Student;
import com.StudentsManagementSystem.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;
import java.util.List;


@Controller
public class controller {  // contains page controllers

    @Autowired
    private StudentService studentService;


    // 🔹 Students list with pagination + search
    @GetMapping("/students")
    public String listStudents(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(required = false) String keyword,
            Model model) {

        int pageSize = 5; // students per page
        Pageable pageable = PageRequest.of(page - 1, pageSize);

        Page<Student> studentPage;

        if (keyword != null && !keyword.isEmpty()) {
            studentPage = studentService.searchStudents(keyword, pageable);
            model.addAttribute("keyword", keyword);
        } else {
            studentPage = studentService.getAllStudents(pageable);
            model.addAttribute("keyword", "");
        }

        model.addAttribute("students", studentPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", studentPage.getTotalPages());

        return "students";
    }

    @GetMapping("/students/new")
    public String newStudentForm(Model model) {
        Student student = new Student();
        model.addAttribute("student", student);
        return "new-student";
    }

    @PostMapping("/students")
    public String saveStudent(@Valid @ModelAttribute("student") Student student,
                              BindingResult result) {
        if (result.hasErrors()) {
            return "new-student"; // reload form with errors
        }
        studentService.saveStudent(student);
        return "redirect:/students";
    }

    @GetMapping("/home")
    public String home(Model model){
        // Total students
        long totalStudents = studentService.countAllStudents();
        model.addAttribute("totalStudents", totalStudents);

        return "home";
    }



    @GetMapping("/students/edit/{id}")
    public String editStudentForm(@PathVariable int id, Model model) {
        model.addAttribute("student", studentService.getById(id));
        return "edit-student";
    }

    @PostMapping("/students/edit/{id}")
    public String updateStudent(@PathVariable int id,
                                @Valid @ModelAttribute("student") Student student,
                                BindingResult result,
                                Model model) {
        if (result.hasErrors()) {
            // return to the edit form if validation fails
            return "edit-student";
        }

        Student existingStudent = studentService.getById(id);
        existingStudent.setFirstName(student.getFirstName());
        existingStudent.setLastName(student.getLastName());
        existingStudent.setEmail(student.getEmail());

        studentService.saveStudent(existingStudent);
        return "redirect:/students";
    }

    @GetMapping("/students/view/{id}")
    public String viewStudent(@PathVariable int id, Model model) {
        Student student = studentService.getById(id);
        model.addAttribute("student", student);
        return "view-student";
    }

    @GetMapping("students/{id}")
    public String deleteStudent(@PathVariable int id) {
        studentService.deleteById(id);
        return "redirect:/students";
    }

}

