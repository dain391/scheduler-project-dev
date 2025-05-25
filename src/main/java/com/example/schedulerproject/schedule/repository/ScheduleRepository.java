package com.example.schedulerproject.schedule.repository;

import com.example.schedulerproject.schedule.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/*
    Repository 인터페이스
    Schedule 엔티티에 대한 CRUD 메서드를 제공하는 JPA 레포지토리
*/
@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    // 기본적인 CRUD 메서드는 JpaRepository에서 제공
}
