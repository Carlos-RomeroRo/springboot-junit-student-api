package com.test.students.services.implementations;

import com.test.students.dto.StudentRequestDto;
import com.test.students.dto.StudentResponseDto;
import com.test.students.entity.Student;
import com.test.students.exception.student.InvalidAgeException;
import com.test.students.exception.student.InvalidCurrentSemesterException;
import com.test.students.exception.student.NameEmptyExcpetion;
import com.test.students.exception.student.StudentNotFoundException;
import com.test.students.mapper.StudentMapper;
import com.test.students.repository.StudentRepository;
import com.test.students.services.interfaces.StudentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    public StudentServiceImpl(StudentRepository studentRepository, StudentMapper studentMapper) {
        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
    }

    @Override
    public StudentResponseDto createStudent(StudentRequestDto requestDto) {
        Student student = studentMapper.toEntity(requestDto);
        // exception for children under 15 years of age
        if (student.getAge() == null || student.getAge() < 15) {
            throw new InvalidAgeException("Students must be at least 15 years old. Provided: " + student.getAge());
        }
        // exception for semester number
        if (student.getAge() == null || student.getCurrentSemester() < 1) {
            throw new InvalidCurrentSemesterException("The semester number must be greater than zero " + student.getCurrentSemester());
        }
        if (student.getName() == null || student.getName().isEmpty()) {
            throw new NameEmptyExcpetion("The name cannot be empty.");
        }
        Student savedStudent = studentRepository.save(student);
        return studentMapper.toResponseDTO(savedStudent);
    }

    @Override
    public List<StudentResponseDto> getAllStudents() {
        List<Student> students = studentRepository.findAll();
        return students.stream()
                .map(studentMapper::toResponseDTO)
                .toList();
    }

    @Override
    public StudentResponseDto getStudentById(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + id));

        return studentMapper.toResponseDTO(student);
    }

    @Override
    public StudentResponseDto updateStudent(Long id, StudentRequestDto requestDto) {
        Student existingStudent = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + id));

        existingStudent.setName(requestDto.getName());
        existingStudent.setUniversityProgram(requestDto.getUniversityProgram());
        existingStudent.setAge(requestDto.getAge());
        existingStudent.setAverage(requestDto.getAverage());
        existingStudent.setCurrentSemester(requestDto.getCurrentSemester());

        Student updatedStudent = studentRepository.save(existingStudent);
        return studentMapper.toResponseDTO(updatedStudent);
    }

    @Override
    public void deleteStudent(Long id) {
        Student existingStudent = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + id));

        studentRepository.delete(existingStudent);
    }
}