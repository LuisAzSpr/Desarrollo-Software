package com.example.security.repository.token;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepository extends JpaRepository<TokenEntity,Integer> {
    void deleteByTokenStr(String tokenStr);
    boolean existsByTokenStr(String tokenStr);
}
