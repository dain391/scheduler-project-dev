package com.example.schedulerproject.schedule.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

/*
    일정 생성 시 클라이언트가 보낸 데이터를 담는 요청 DTO
*/
@Getter
@NoArgsConstructor
public class ScheduleRequestDto {
    private Long userId;
    private String title;
    private String contents;
}
