package com.example.schedulerproject.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/*
    유저 생성 요청용 DTO
    - 클라이언트가 유저 생성 시 보내는 데이터 구조
*/
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserCreateRequestDto {

    @NotBlank(message = "유저명은 필수 입력값입니다.")
    @Size(max = 20, message = "유저명은 최대 20자까지 가능합니다.")
    private String name;

    @NotBlank(message = "비밀번호는 필수 입력값입니다.")
    @Size(min = 4, max = 20, message = "비밀번호는 4~20자로 가능합니다.")
    private String password;

    @NotBlank(message = "이메일은 필수 입력값입니다.")
    @Email(message = "유효한 이메일 형식이어야 합니다.")
    @Size(max = 50, message = "이메일은 최대 50자까지 가능합니다.")
    private String email;
}