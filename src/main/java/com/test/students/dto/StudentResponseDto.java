package com.test.students.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class StudentResponseDto {
    private Long id;
    private String name;
    private String universityProgram;
    private Integer age;
    private Double average;
    private Integer currentSemester;
}
