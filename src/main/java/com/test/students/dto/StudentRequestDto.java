package com.test.students.dto;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentRequestDto {
    private String name;
    private String universityProgram;
    private Integer age;
    private Double average;
    private Integer currentSemester;
}
