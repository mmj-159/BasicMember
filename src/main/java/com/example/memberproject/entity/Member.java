package com.example.memberproject.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    // 어차피 ID는 DB에서 생성하니까 name만 생성자로 생성
    public Member(String name) {
        this.name = name;
    }

    public void update(String name) {
        this.name = name;
    }
}
