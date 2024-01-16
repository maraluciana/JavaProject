package com.project.demo.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import com.project.demo.model.Member;
import com.project.demo.model.MembershipType;
import com.project.demo.repository.MemberRepository;

class MemberServiceTest {

    @Mock
    private MemberRepository memberRepository;

    @InjectMocks
    private MemberService memberService;

    private Member member;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        member = new Member("Alice Smith", "alice@example.com", MembershipType.STANDARD);
    }

    @Test
    void addMemberSuccess() {
        when(memberRepository.findByEmail(member.getEmail())).thenReturn(Optional.empty());
        when(memberRepository.save(any(Member.class))).thenReturn(member);

        Member savedMember = memberService.addMember(member);
        assertNotNull(savedMember);
        verify(memberRepository).save(any(Member.class));
    }

    @Test
    void addMemberFailureEmailExists() {
        when(memberRepository.findByEmail(member.getEmail())).thenReturn(Optional.of(member));

        assertThrows(IllegalStateException.class, () -> memberService.addMember(member));
        verify(memberRepository, never()).save(any(Member.class));
    }

}
