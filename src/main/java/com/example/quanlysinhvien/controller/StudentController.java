// package com.example.quanlysinhvien.controller;

// import java.util.List;
// import java.util.UUID;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.web.bind.annotation.DeleteMapping;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.PutMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.web.bind.annotation.RestController;

// import com.example.quanlysinhvien.entity.Student;
// import com.example.quanlysinhvien.service.StudentService;

// @RestController
// @RequestMapping("/students")
// public class StudentController {

//     @Autowired
//     private StudentService studentService;

//     @GetMapping
//     public List<Student> listStudents(
//             @RequestParam(required = false) String keyword) {
//         return studentService.search(keyword);
//     }

//     @GetMapping("/{id}")
//     public Student getStudent(@PathVariable UUID id) {
//         return studentService.getById(id);
//     }

//     @PostMapping
//     public Student createStudent(@RequestBody Student student) {
//         return studentService.save(student);
//     }

//     @PutMapping("/{id}")
//     public Student updateStudent(
//             @PathVariable UUID id,
//             @RequestBody Student student) {
//         student.setId(id);
//         return studentService.save(student);
//     }

//     @DeleteMapping("/{id}")
//     public void deleteStudent(@PathVariable UUID id) {
//         studentService.delete(id);
//     }
// }

package com.example.quanlysinhvien.controller;

import java.util.List;
import java.util.UUID; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.quanlysinhvien.entity.Student;
import com.example.quanlysinhvien.service.StudentService;

@RestController
@RequestMapping("/students")
@CrossOrigin("*") // Cho phép file index.html gọi API không bị lỗi CORS
public class StudentController {

    @Autowired
    private StudentService studentService;

    // Yêu cầu 1: API Thêm sinh viên (1.0 đ)
    @PostMapping
    public Student createStudent(@RequestBody Student student) {
        return studentService.save(student);
    }

    // Yêu cầu 2: API Xóa sinh viên - @PostMapping("/delete/{id}") (1.0 đ)
    @PostMapping("/delete/{id}")
    public void deleteStudent(@PathVariable UUID id) {
        studentService.delete(id);
    }

    // Yêu cầu 3: API Tìm kiếm sinh viên theo tên - @GetMapping("/search") (1.0 đ)
    @GetMapping("/search")
    public List<Student> searchStudents(@RequestParam(required = false) String keyword) {
        return studentService.search(keyword);
    }

    // Yêu cầu 4: API Lấy sinh viên theo ID - @GetMapping("/{id}") (1.0 đ)
    @GetMapping("/{id}")
    public Student getStudent(@PathVariable UUID id) {
        return studentService.getById(id);
    }

    // Yêu cầu 5: API Lấy danh sách sinh viên Get All - @GetMapping (1.0 đ)
    @GetMapping
    public List<Student> listStudents() {
        return studentService.search(null); // hoặc studentService.getAll();
    }

    // Yêu cầu 6: API Cập nhật sinh viên - @PostMapping("/update/{id}") (1.0 đ)
    @PostMapping("/update/{id}")
    public Student updateStudent(
            @PathVariable UUID id,
            @RequestBody Student student) {
        student.setId(id);
        return studentService.save(student);
    }
}