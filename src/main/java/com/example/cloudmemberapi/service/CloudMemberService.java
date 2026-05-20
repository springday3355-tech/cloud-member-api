package com.example.cloudmemberapi.service;


import com.example.cloudmemberapi.dto.MemberRequestDto;
import com.example.cloudmemberapi.dto.MemberResponseDto;
import com.example.cloudmemberapi.entity.CloudMember;
import com.example.cloudmemberapi.repository.CloudMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CloudMemberService {

    private final CloudMemberRepository cloudMemberRepository;

    @Transactional
    public MemberResponseDto saveMember(MemberRequestDto requestDto) {
        // 1. DTO에서 Entity로 변환
        CloudMember member = CloudMember.builder()
                .name(requestDto.getName())
                .age(requestDto.getAge())
                .mbti(requestDto.getMbti())
                .build();

        // 2. Repository를 통해 DB에 저장
        // 리포지토리에 저장하면 DB가 생성해준 ID가 포함된 엔티티가 반환됌
        CloudMember savedMember = cloudMemberRepository.save(member);

        // 3. Entity -> ResponseDto 변환 후 반환
        return new MemberResponseDto(
                savedMember.getId(),
                savedMember.getName(),
                savedMember.getAge(),
                savedMember.getMbti()
        );
    }

    @Transactional(readOnly = true)
    public MemberResponseDto getMember(Long id) {

        // 1. Repository를 통해 DB에서 id로 member를 찾음/ 해당id를 가진 데이터가 없으면 예외발생
        CloudMember member = cloudMemberRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("해당 팀원이 존재하지 않습니다. id= "+ id));

        // 2. 찾은 엔티티를 ResponseDto로 변환해서 반환함
        return new MemberResponseDto(
                member.getId(),
                member.getName(),
                member.getAge(),
                member.getMbti()
        );



    }
}
