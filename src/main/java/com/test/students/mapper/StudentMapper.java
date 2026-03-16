package com.test.students.mapper;

import com.test.students.dto.StudentRequestDto;
import com.test.students.dto.StudentResponseDto;
import com.test.students.entity.Student;
import org.springframework.stereotype.Component;

@Component
public class StudentMapper {

    public Student toEntity(StudentRequestDto dto){
        return Student.builder()
                .name(dto.getName())
                .universityProgram(dto.getUniversityProgram())
                .age(dto.getAge())
                .average(dto.getAverage())
                .currentSemester(dto.getCurrentSemester())
                .build();
    } // Lo que ingresa (conveirte lo que llega del cliente en una entidad)

    public StudentResponseDto toResponseDTO (Student student){
        return StudentResponseDto.builder()
                .id(student.getId())
                .name(student.getName())
                .universityProgram(student.getUniversityProgram())
                .age(student.getAge())
                .average(student.getAverage())
                .currentSemester(student.getCurrentSemester())
                .build();
    } // Convierte la entidad que viene de la BD en un objeto de respuesta para un cliente

}
