package com.example.schedulerproject.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

/*
    로그인 요청을 담는 DTO
    - 클라이언트가 로그인을 시도할 때 이메일과 비밀번호를 서버로 보낼 때 사용하는 객체
*/
@Getter
public class LoginRequestDto {

    @Email(message = "유효한 이메일 형식을 입력해 주세요.")
    @NotBlank(message = "아이디를 입력해 주세요")
    private String email;

    @NotBlank(message = "비밀번호를 입력해 주세요.")
    private String password;
}
