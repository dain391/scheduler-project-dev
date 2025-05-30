package com.example.schedulerproject.schedule.service;

import com.example.schedulerproject.schedule.dto.ScheduleRequestDto;
import com.example.schedulerproject.schedule.dto.ScheduleResponseDto;
import com.example.schedulerproject.schedule.entity.Schedule;
import com.example.schedulerproject.schedule.repository.ScheduleRepository;
import com.example.schedulerproject.user.entity.User;
import com.example.schedulerproject.user.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/*
    일정 생성, 조회 등의 비즈니스 로직을 처리하는 서비스 계층
*/
@Service // 스프링 서비스 빈 등록
@Transactional(readOnly = true) // 조회 메서드는 트랜잭션 읽기 전용으로 성능 최적화
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;

    // 롬복 @RequiredArgsConstructor 대체 생성자 주입
    public ScheduleService(ScheduleRepository scheduleRepository, UserRepository userRepository) {
        this.scheduleRepository = scheduleRepository;
        this.userRepository = userRepository;
    }

    // 전체 일정 조회
    public List<ScheduleResponseDto> findAllSchedules() {
        List<Schedule> schedules = scheduleRepository.findAll();

        return schedules.stream() // 리스트 반복하면서
                .map(ScheduleResponseDto::new) // 하나하나 ScheduleResponseDto로 바꿈
                .toList(); // 다시 리스트로 모음
    }

    // ID로 일정 조회
    public ScheduleResponseDto getSchedule(Long id) {
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 일정이 없습니다. id=" + id));
        return new ScheduleResponseDto(schedule);
    }


    // 일정 생성
    @Transactional // 쓰기 작업(생성, 수정, 삭제)은 @Transactional을 붙여 변경 감지 + DB 반영 보장
    public Schedule createdSchedule(ScheduleRequestDto dto, HttpSession session) {
        // 세션에서 로그인한 사용자 정보 꺼내기
        User loginUser = (User) session.getAttribute("user");
        if (loginUser == null) {
            throw new IllegalArgumentException("로그인이 필요합니다.");
        }

        // 세션의 사용자 정보로 일정 생성
        Schedule schedule = new Schedule();
        schedule.setUser(loginUser);
        schedule.setTitle(dto.getTitle());
        schedule.setContents(dto.getContents());

        return scheduleRepository.save(schedule);
    }

    // 일정 수정
    @Transactional
    public ScheduleResponseDto updateSchedule(Long id, ScheduleRequestDto requestDto) {
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 일정이 없습니다. id=" + id));

        schedule.update(requestDto.getTitle(), requestDto.getContents());

        // 변경 내용 즉시 DB에 반영
        scheduleRepository.flush();

        // DB에서 다시 조회해서 최신 updatedAt을 가진 객체로 응답 생성
        Schedule updatedSchedule = scheduleRepository.findById(id).get();
        return new ScheduleResponseDto(updatedSchedule);
    }

    // 일정 삭제
    @Transactional
    public void deleteSchedule(Long id) {
        scheduleRepository.deleteById(id);
    }
}
