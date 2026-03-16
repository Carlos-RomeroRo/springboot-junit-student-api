package com.test.students.services.interfaces;

import com.test.students.dto.StudentRequestDto;
import com.test.students.dto.StudentResponseDto;

import java.util.List;

public interface StudentService {

    StudentResponseDto createStudent(StudentRequestDto requestDto);
    List<StudentResponseDto> getAllStudents();
    StudentResponseDto getStudentById(Long id);
    StudentResponseDto updateStudent(Long id, StudentRequestDto requestDto);
    void deleteStudent(Long id);
}
