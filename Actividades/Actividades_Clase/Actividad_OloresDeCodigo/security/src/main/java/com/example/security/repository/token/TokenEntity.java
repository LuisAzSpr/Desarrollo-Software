package com.example.security.repository.token;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="tokens")
public class TokenEntity {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(unique = true)
    private String tokenStr;

    private String username;
}
