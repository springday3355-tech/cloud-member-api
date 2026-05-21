package com.example.cloudmemberapi.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class CloudMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // 속
    private Long id;

    private String name;

    private Integer age;

    private String mbti;


    // S3 이미지 URL을 저장할 필드
    private String profileImageUrl;

    // 이미지 Url 업데이트하는 메서드
    public void updateProfileImage(String profileImageUrl){
        this.profileImageUrl = profileImageUrl;
    }

}
