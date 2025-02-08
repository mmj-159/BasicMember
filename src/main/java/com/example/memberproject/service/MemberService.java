package com.example.memberproject.service;

import com.example.memberproject.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    //3 layered architecture 구조로 서비스는 레포지토리를 주입받아야 함
    private final MemberRepository memberRepository;
}
