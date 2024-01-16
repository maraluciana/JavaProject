package com.project.demo.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import com.project.demo.model.Member;
import com.project.demo.model.MembershipType;
import com.project.demo.service.MemberService;

class MemberControllerTest {

    @Mock
    private MemberService memberService;

    @InjectMocks
    private MemberController memberController;

    private Member member;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        member = new Member("Bob Marley", "bob@example.com", MembershipType.PREMIUM);
    }

    @Test
    void addMember() {
        when(memberService.addMember(any(Member.class))).thenReturn(member);

        ResponseEntity<Member> response = memberController.addMember(member);
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
    }

}
