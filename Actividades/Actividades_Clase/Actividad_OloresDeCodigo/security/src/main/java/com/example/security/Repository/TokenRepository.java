package com.example.security.Repository;

import com.example.security.Repository.tokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TokenRepository extends JpaRepository<tokenEntity,Integer> {
    void deleteByTokenStr(String tokenStr);
    boolean existsByTokenStr(String tokenStr);
    List<tokenEntity> findByusername(String username);

}
