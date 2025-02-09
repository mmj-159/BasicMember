package com.example.memberproject.controller;

import com.example.memberproject.dto.MemberRequestDto;
import com.example.memberproject.dto.MemberResponseDto;
import com.example.memberproject.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {

    //3 layered architecture 구조로 컨트롤러는 서비스를 주입받아야 함
    private final MemberService memberService;

    @PostMapping("/members")
    public MemberResponseDto save(@RequestBody MemberRequestDto dto) {

    }
}
