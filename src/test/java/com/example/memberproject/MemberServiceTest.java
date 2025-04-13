package com.example.memberproject;

import com.example.memberproject.dto.MemberRequestDto;
import com.example.memberproject.dto.MemberResponseDto;
import com.example.memberproject.entity.Member;
import com.example.memberproject.repository.MemberRepository;
import com.example.memberproject.service.MemberService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

class MemberServiceTest {

    private MemberService memberService;
    private MemberRepository memberRepository;

    @BeforeEach
    void setUp() {
        memberRepository = mock(MemberRepository.class);
        memberService = new MemberService(memberRepository);
    }

    @Test
    @DisplayName("멤버 저장 서비스 테스트")
    void save() {
        // given
        MemberRequestDto dto = new MemberRequestDto("홍길동");
        Member savedMember = new Member("홍길동");
        savedMember.update("홍길동");

        when(memberRepository.save(any(Member.class)))
                .thenReturn(new Member("홍길동"));

        // when
        MemberResponseDto response = memberService.save(dto);

        // then
        assertThat(response.getName()).isEqualTo("홍길동");
        verify(memberRepository).save(any(Member.class));
    }

    @Test
    @DisplayName("멤버 전체 조회 서비스 테스트")
    void findAll() {
        // given
        List<Member> members = Arrays.asList(
                new Member("홍길동"),
                new Member("김철수")
        );

        when(memberRepository.findAll()).thenReturn(members);

        // when
        List<MemberResponseDto> responses = memberService.findAll();

        // then
        assertThat(responses).hasSize(2);
        assertThat(responses.get(0).getName()).isEqualTo("홍길동");
        assertThat(responses.get(1).getName()).isEqualTo("김철수");
    }

    @Test
    @DisplayName("멤버 단건 조회 서비스 테스트")
    void findById() {
        // given
        Member member = new Member("홍길동");
        when(memberRepository.findById(1L)).thenReturn(Optional.of(member));

        // when
        MemberResponseDto response = memberService.findById(1L);

        // then
        assertThat(response.getName()).isEqualTo("홍길동");
    }

    @Test
    @DisplayName("멤버 수정 서비스 테스트")
    void update() {
        // given
        Member member = new Member("홍길동");
        when(memberRepository.findById(1L)).thenReturn(Optional.of(member));

        MemberRequestDto updateDto = new MemberRequestDto("임꺽정");

        // when
        MemberResponseDto response = memberService.update(1L, updateDto);

        // then
        assertThat(response.getName()).isEqualTo("임꺽정");
    }

    @Test
    @DisplayName("멤버 삭제 서비스 테스트")
    void deleteById() {
        // given
        when(memberRepository.existsById(1L)).thenReturn(true);

        // when
        memberService.deleteById(1L);

        // then
        verify(memberRepository).deleteById(1L);
    }

    @Test
    @DisplayName("멤버 삭제 실패 테스트 (존재하지 않는 id)")
    void deleteById_fail() {
        // given
        when(memberRepository.existsById(1L)).thenReturn(false);

        // when & then
        assertThatThrownBy(() -> memberService.deleteById(1L))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("id에 해당하는게 없다니까");
    }
}