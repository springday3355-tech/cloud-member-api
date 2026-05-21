package com.example.cloudmemberapi.config;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class MemberProfileController {

    private final S3Service s3service;

    // 1. 프로필 이미지 업로드

    @PostMapping("/{id}/profile-image")
    public ResponseEntity<String> uploadProfileImage(
            @PathVariable Long id,
            @RequestParam("file")MultipartFile file
            ) throws IOException{
        String result = s3service.uploadProfileImage(id, file);
        return ResponseEntity.ok(result);
    }

    // 2. Presigned URL 반환

    @GetMapping("/{id}/profile-image")
    public ResponseEntity<String> getProfileImage(@PathVariable Long id){
        String presignedUrl = s3service.getPresignedUrl(id);
        return ResponseEntity.ok(presignedUrl);
    }












}
