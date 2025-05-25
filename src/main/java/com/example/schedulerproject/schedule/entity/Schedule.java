package com.example.schedulerproject.schedule.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

/*
    Schedule 엔티티 클래스
    DB의 schedules 테이블과 매핑된다.
*/
@Entity
@Table(name = "schedules")
@EntityListeners(AuditingEntityListener.class) // createdAt, updatedAt 자동 기록
@Getter // getter 메서드 자동 생성
@NoArgsConstructor // 파라미터 없는 기본 생성자 자동 생성
@AllArgsConstructor // 모든 필드를 받는 생성자 자동 생성
@Builder // 객체를 편리하게 생성하도록 빌더 패턴 적용

public class Schedule {
    @Id // 기본 키 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20)
    private String username;

    @Column(nullable = false, length = 20)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String contents;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(updatable = true)
    private LocalDateTime updatedAt;

    /*
    일정 수정 메서드
    title과 contents 수정 시 사용
    */
    public void update(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }
}
