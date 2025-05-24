package com.example.schedulerproject.service;

import com.example.schedulerproject.dto.ScheduleRequestDto;
import com.example.schedulerproject.dto.ScheduleResponseDto;
import com.example.schedulerproject.entity.Schedule;
import com.example.schedulerproject.repository.ScheduleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/*
    일정 생성, 조회 등의 비즈니스 로직을 처리하는 서비스 계층
*/
@Service // 스프링 서비스 빈 등록
@Transactional(readOnly = true) // 조회 메서드는 트랜잭션 읽기 전용으로 성능 최적화
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    // 롬복 @RequiredArgsConstructor 대체 생성자 주입
    public ScheduleService(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
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
    public Schedule createSchedule(ScheduleRequestDto requestDto) {
        Schedule schedule = Schedule.builder()
                .username(requestDto.getUsername())
                .title(requestDto.getTitle())
                .contents(requestDto.getContents())
                .build();
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
