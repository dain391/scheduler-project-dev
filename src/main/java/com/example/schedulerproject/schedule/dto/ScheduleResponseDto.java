package com.example.schedulerproject.schedule.dto;

import com.example.schedulerproject.schedule.entity.Schedule;
import lombok.Getter;

import java.time.LocalDateTime;

/*
    클라이언트에게 반환할 일정 응답 데이터를 담는 DTO
*/
@Getter
public class ScheduleResponseDto {
    private final Long id;
    private final String username;
    private final String title;
    private final String contents;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public ScheduleResponseDto(Schedule schedule) {
        this.id = schedule.getId();
        this.username = schedule.getUser().getName();
        this.title = schedule.getTitle();
        this.contents = schedule.getContents();
        this.createdAt = schedule.getCreatedAt();
        this.updatedAt = schedule.getUpdatedAt();
    }
}
