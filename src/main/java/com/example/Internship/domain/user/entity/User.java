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
    private String UserName;
    private String Password;
    private String Nickname;
    private UserRole role;

}
