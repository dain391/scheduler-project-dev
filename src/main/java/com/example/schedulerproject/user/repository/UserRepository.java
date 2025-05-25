package com.example.schedulerproject.user.repository;

import com.example.schedulerproject.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/*
    User 엔티티에 대한 DB 접근을 처리하는 인터페이스
    - JpaRepository를 상속하여 CRUD 메서드 기본 제공
*/
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // 필요시 커스텀 쿼리 메서드 정의
}
