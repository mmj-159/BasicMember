package com.example.memberproject.dto;

import lombok.Getter;

@Getter
public class MemberRequestDto {

    private String name;

    public MemberRequestDto(String name) {
        this.name = name;
    }
}
