package com.test.students.controller;


import com.test.students.dto.StudentRequestDto;
import com.test.students.dto.StudentResponseDto;
import com.test.students.services.implementations.StudentServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentServiceImpl studentService;

    public StudentController(StudentServiceImpl studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<StudentResponseDto> createStudent(@RequestBody StudentRequestDto requestDto){
        StudentResponseDto  createStudent = studentService.createStudent(requestDto);
        return new ResponseEntity<>(createStudent, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<StudentResponseDto>> getAllStudent(){
        List<StudentResponseDto> students = studentService.getAllStudents();
        return  ResponseEntity.ok(students);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentResponseDto> getStudentById(@PathVariable Long id){
        StudentResponseDto student = studentService.getStudentById(id);
        return ResponseEntity.ok(student);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentResponseDto> updateStudent(@PathVariable Long id, @RequestBody StudentRequestDto studentRequestDto){
        StudentResponseDto updateStudent = studentService.updateStudent(id,studentRequestDto);
        return ResponseEntity.ok(updateStudent);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id){
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }

}
