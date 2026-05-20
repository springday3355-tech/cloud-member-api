package com.example.cloudmemberapi.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberRequestDto {

    private String name;
    private Integer age;
    private String mbti;

    public MemberRequestDto(String name, Integer age, String mbti) {
        this.name = name;
        this.age = age;
        this.mbti = mbti;
    }
}
