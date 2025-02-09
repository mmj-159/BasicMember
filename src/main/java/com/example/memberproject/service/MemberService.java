package com.example.memberproject.service;

import com.example.memberproject.dto.MemberRequestDto;
import com.example.memberproject.dto.MemberResponseDto;
import com.example.memberproject.entity.Member;
import com.example.memberproject.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {
    //3 layered architecture 구조로 서비스는 레포지토리를 주입받아야 함
    private final MemberRepository memberRepository;

    @Transactional
    public MemberResponseDto save(MemberRequestDto dto) {
        Member member = new Member(dto.getName());
        Member savedMember = memberRepository.save(member);

        return new MemberResponseDto(savedMember.getId(), savedMember.getName());
    }

    @Transactional
    public List<MemberResponseDto> findAll() {
        List<Member> members = memberRepository.findAll();

        List<MemberResponseDto> dtos = new ArrayList<>();
        for (Member member : members) {
            MemberResponseDto dto = new MemberResponseDto(member.getId(), member.getName());
            dtos.add(dto);
        }
        return dtos;
    }

    @Transactional
    public MemberResponseDto findById(Long id){
        Member member = memberRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("id에 해당하는 멤버 없음")
        );
        return new MemberResponseDto(member.getId(), member.getName());
    }

    @Transactional
    public MemberResponseDto update(Long id, MemberRequestDto dto) {
        Member member = memberRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("id에 해당하는 멤버 없음!")
        );

        member.update(dto.getName());

        return new MemberResponseDto(member.getId(), member.getName());
    }

    @Transactional
    public void deleteById(Long id) {
        if (!memberRepository.existsById(id)) {
            throw new IllegalArgumentException("id에 해당하는게 없단다~");
        }

        memberRepository.deleteById(id);
    }
}
