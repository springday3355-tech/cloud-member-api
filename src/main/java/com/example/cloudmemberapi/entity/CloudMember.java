package com.example.cloudmemberapi.entity;


import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor

public class CloudMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // 속
    private Long id;

    private String name;

    private Integer age;

    private String mbti;

    // 생성자
    public CloudMember (Long id, String name, Integer age, String mbti){
        this.id = id;
        this.name = name;
        this.age = age;
        this.mbti = mbti;
    }

    // 게터
    public Long getId(){
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public String getMbti() {
        return mbti;
    }

}
