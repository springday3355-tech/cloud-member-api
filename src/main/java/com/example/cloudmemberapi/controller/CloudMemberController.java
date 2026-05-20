package com.example.cloudmemberapi.controller;


import com.example.cloudmemberapi.dto.MemberRequestDto;
import com.example.cloudmemberapi.dto.MemberResponseDto;
import com.example.cloudmemberapi.service.CloudMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/members")
public class CloudMemberController {

    private final CloudMemberService cloudMemberService;

    @PostMapping
    public ResponseEntity<MemberResponseDto> createMember(@RequestBody MemberRequestDto requestDto){
        // service 호출해서 저장하고 결과 DTO 방기
        MemberResponseDto responseDto = cloudMemberService.saveMember(requestDto);
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MemberResponseDto> getMember(@PathVariable Long id){

        // 0. 클라이언트가 보내준 id 값을 DB에서 찾는다-> 데이터가 있으면 DTO에 담아서 보내준다-> 없으면 에러를 던진다

        // 1. Service를 호춣해 단건 조회 수행
        MemberResponseDto responseDto = cloudMemberService.getMember(id);

        // 2. 조회한 데이터 반환
        return  ResponseEntity.ok(responseDto);

    }



}
