package com.example.cloudmemberapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MemberResponseDto {

    private Long id;
    private String name;
    private Integer age;
    private String mbti;
}
