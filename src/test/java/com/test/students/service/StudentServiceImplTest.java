package com.test.students.service;

import com.test.students.dto.StudentRequestDto;
import com.test.students.dto.StudentResponseDto;
import com.test.students.entity.Student;
import com.test.students.exception.student.InvalidAgeException;
import com.test.students.exception.student.InvalidCurrentSemesterException;
import com.test.students.exception.student.NameEmptyExcpetion;
import com.test.students.mapper.StudentMapper;
import com.test.students.repository.StudentRepository;
import com.test.students.services.implementations.StudentServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class StudentServiceImplTest {

    @Mock
    private StudentRepository studentRepository;
    @Mock
    private StudentMapper studentMapper;
    @InjectMocks
    private StudentServiceImpl studentService;
    private StudentRequestDto studentRequestDto;
    private StudentResponseDto studentResponseDto;
    private Student student;

    @BeforeEach
    void setUp(){
        studentRequestDto = StudentRequestDto.builder()
                .name("Juan Pérez")
                .universityProgram("Ingeniería")
                .age(20)
                .average(8.5)
                .currentSemester(3)
                .build();

        student = Student.builder()
                .id(1L)
                .name("Juan Pérez")
                .universityProgram("Ingeniería")
                .age(20)
                .average(8.5)
                .currentSemester(3)
                .build();

        studentResponseDto = StudentResponseDto.builder()
                .id(1L)
                .name("Juan Pérez")
                .universityProgram("Ingeniería")
                .age(20)
                .average(8.5)
                .currentSemester(3)
                .build();
    }

    @Test
    void shouldCreateStudentSucessfully(){
        //Given - Arrange (preparar datos)
        when(studentMapper.toEntity(studentRequestDto)).thenReturn(student);
        when(studentRepository.save(student)).thenReturn(student);
        when(studentMapper.toResponseDTO(student)).thenReturn(studentResponseDto);

        // When - Act (ejecutar método)
        StudentResponseDto result = studentService.createStudent(studentRequestDto);

        // Then - Assert (Verificar resultados)
        assertThat(result).isEqualTo(studentResponseDto);
        verify(studentMapper).toEntity(studentRequestDto);
        verify(studentRepository).save(student);
        verify(studentMapper).toResponseDTO(student);
    }

    @Test
    void shouldThrowExceptionWhenCreateStudentFails() {
        // Given - Arrange (preparar datos)
        when(studentMapper.toEntity(studentRequestDto)).thenReturn(student);
        when(studentRepository.save(student)).thenThrow(new RuntimeException("Database error"));

        // When - Act (ejecutar método) y Then - Assert (Verificar que lance excepción)
        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> studentService.createStudent(studentRequestDto)
        );

        // Then - Assert (Verificar mensaje de excepción)
        assertThat(exception.getMessage()).isEqualTo("Database error");
        verify(studentMapper).toEntity(studentRequestDto);
        verify(studentRepository).save(student);
        // No se debe llamar al mapper de respuesta porque falló el save
        verify(studentMapper, never()).toResponseDTO(any());
    }

    // Verifica si la validación para estudiantes menores de 14 años es correcta
    @Test
    void shouldThrowInvlidAgeExceptionStudent(){
        // Given - Arrange
        // Enviado por el cliente
        StudentRequestDto underageStudentRequest = StudentRequestDto.builder()
                .name("Ana García")
                .universityProgram("Medicina")
                .age(14)
                .average(9.0)
                .currentSemester(1)
                .build();
        // Entidad
        Student underageStudent  = Student.builder()
                .id(2L)
                .name("Ana García")
                .universityProgram("Medicina")
                .age(14)
                .average(9.0)
                .currentSemester(1)
                .build();
        // When - Act (ejecutar método) y Then - Assert (Verificar que lance excepción)
        when(studentMapper.toEntity(underageStudentRequest)).thenReturn(underageStudent);
        InvalidAgeException exception = assertThrows(
                InvalidAgeException.class,
                () -> studentService.createStudent(underageStudentRequest)
        );

        // Verificar mensaje de la excepción
        assertThat(exception.getMessage()).contains("Students must be at least 15 years old");
        assertThat(exception.getMessage()).contains("14");

        // Verificar que no se intentó guardar en el repositorio
        verify(studentMapper).toEntity(underageStudentRequest);
        verify(studentRepository, never()).save(any());
    }

    @Test
    void shouldThrowInvalidCurrentSemesterException(){
        // Given - Arrange
        // Enviado por el cliente
        StudentRequestDto underSemesterStudentRequest = StudentRequestDto.builder()
                .name("Pedro Moreno")
                .universityProgram("Biología")
                .age(33)
                .average(8.0)
                .currentSemester(0)
                .build();
        // Entidad
        Student underSemesterStudent  = Student.builder()
                .id(2L)
                .name("Pedro Moreno")
                .universityProgram("Biología")
                .age(33)
                .average(8.0)
                .currentSemester(0)
                .build();
        // When - Act (ejecutar método) y Then - Assert (Verificar que lance excepción)
        when(studentMapper.toEntity(underSemesterStudentRequest)).thenReturn(underSemesterStudent);
        InvalidCurrentSemesterException exception = assertThrows(
                InvalidCurrentSemesterException.class,
                () -> studentService.createStudent(underSemesterStudentRequest)
        );
        // Verificar mensaje de la excepción
        assertThat(exception.getMessage()).contains("The semester number must be greater than zero");
        assertThat(exception.getMessage()).contains("0");

        // Verificar que no se intentó guardar en el repositorio
        verify(studentMapper).toEntity(underSemesterStudentRequest);
        verify(studentRepository, never()).save(any());
    }

    @Test
    void shouldThrowNameEmptyException(){
        StudentRequestDto underNameStudentRequest = StudentRequestDto.builder()
                .name("")
                .universityProgram("Derecho")
                .age(23)
                .average(7.0)
                .currentSemester(4)
                .build();

        Student underNameStudent = Student.builder()
                .id(2L)
                .name("")
                .universityProgram("Derecho")
                .age(23)
                .average(7.0)
                .currentSemester(4)
                .build();

        when(studentMapper.toEntity(underNameStudentRequest)).thenReturn(underNameStudent);
        NameEmptyExcpetion exception = assertThrows(
                NameEmptyExcpetion.class,
                () -> studentService.createStudent(underNameStudentRequest)
        );

        // Verificar mensaje de la excepción
        assertThat(exception.getMessage()).contains("The name cannot be empty.");
        assertThat(exception.getMessage()).contains("");

        // Verificar que no se intentó guardar en el repositorio
        verify(studentMapper).toEntity(underNameStudentRequest);
        verify(studentRepository, never()).save(any());

    }



}
