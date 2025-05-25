package Lv3.entity;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

/*
    DB의 users 테이블과 매핑되는 클래스
    - 유저명, 이메일, 생성일, 수정일 속성
    - JPA Auditing을 통해 생성일과 수정일 자동 기록
*/
@Entity // 이클래스가 JPA 엔티티임을 나타냄
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor // 기본 생성자 생성
@AllArgsConstructor // 모든 필드를 포함한 생성자 생성
@Builder // 객체 생성 시 빌더 패턴 사용 가능
@EntityListeners(AuditingEntityListener.class) // 생성, 수정 시간 자동 반영을 위한 리스너 등록
public class User {

    @Id // 기본 키(PK) 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment 전략
    private Long id; // 유저 고유 ID

    @Column(nullable = false, length = 20)
    private String name; // 이름

    @Column(nullable = false, length = 20)
    private String password; // 비밀번호

    @Column(nullable = false, length = 50, unique = true) // 중복 불가
    private String email; // 이메일

    @CreatedDate // 엔티티 생성 시 자동 저장
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt; // 생성일

    @LastModifiedDate // 엔티티 수정시 자동 갱신
    @Column(nullable = false)
    private LocalDateTime updatedAt; // 수정일
}