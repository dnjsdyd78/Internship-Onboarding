package com.example.Internship.domain.user.entity;

import com.example.Internship.domain.user.enums.UserRole;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Getter
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String username;
    private String password;
    private String nickname;
    private UserRole role;

    public User(String username, String encodedPassword, String nickname, UserRole userRole) {
        this.username = username;
        this.password = encodedPassword;
        this.nickname = nickname;
        this.role = userRole;
    }

    public User(Long userId, String nickname, UserRole role) {
        this.id = userId;
        this.nickname = nickname;
        this.role = role;
    }

}
