package com.example.cloudmemberapi.config;


import com.example.cloudmemberapi.entity.CloudMember;
import com.example.cloudmemberapi.repository.CloudMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.GetObjectPresignRequest;
import software.amazon.awssdk.services.s3.presigner.model.PresignedGetObjectRequest;

import java.io.IOException;
import java.time.Duration;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class S3Service {

    private final S3Client s3Client;
    private final S3Presigner s3Presigner;
    private final CloudMemberRepository cloudMemberRepository;

    @Value("${spring.cloud.aws.s3.bucket}")
    private String bucket;

    //  프로필 이미지 업로드
    @Transactional
    public String uploadProfileImage(Long memberId, MultipartFile file)throws IOException {
        CloudMember member = cloudMemberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("해당 회원이 없습니다. id= " + memberId));
        // 파일 중복 막기 위해 UUID 적용
        String s3Key = "profile/" + UUID.randomUUID() + "_" + file.getOriginalFilename();

        // S3 파일에 업로드
        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket(bucket)
                .key(s3Key)
                .contentType(file.getContentType())
                .build();
        s3Client.putObject(putObjectRequest, RequestBody.fromInputStream(file.getInputStream(), file.getSize()));

        // DB에 S3 key 업데이트
        member.updateProfileImage(s3Key);

        return "업로드 성공! S3 Key: " + s3Key;
    }

    // 7일짜리 Presigned URL 생성 기능
    @Transactional(readOnly = true)
    public String getPresignedUrl(Long memberId) {
        CloudMember member = cloudMemberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("해당 회원이 없습니다. id=" + memberId));

        String s3Key = member.getProfileImageUrl();
        if (s3Key == null || s3Key.isEmpty()) {
            throw new IllegalArgumentException("등록된 프로필 이미지가 없습니다.");
        }

        // 7일 동안만 유효한 다운로드 다운로드 링크 생성
        GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                .bucket(bucket)
                .key(s3Key)
                .build();

        GetObjectPresignRequest presignRequest = GetObjectPresignRequest.builder()
                .signatureDuration(Duration.ofDays(7)) // 유효기간 7일 설정
                .getObjectRequest(getObjectRequest)
                .build();

        PresignedGetObjectRequest presignedRequest = s3Presigner.presignGetObject(presignRequest);

        return presignedRequest.url().toString(); // 클라이언트가 다운로드할 수 있는 무적의 URL 반환
    }
}