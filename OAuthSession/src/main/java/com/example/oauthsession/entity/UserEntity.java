package com.example.oauthsession.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 프록시 때문에 PROTECTED까지만 내린다.
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String email;
    private String role;

    @Builder
    public UserEntity(String username, String email, String role) {
        this.username = username;
        this.email = email;
        this.role = role;
    }

    public void changeInfo(String username, String email) {
        this.username = username;
        this.email = email;
    }

}
