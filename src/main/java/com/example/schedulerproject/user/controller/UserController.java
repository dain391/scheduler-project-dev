package com.example.schedulerproject.user.controller;

import com.example.schedulerproject.user.dto.LoginRequestDto;
import com.example.schedulerproject.user.dto.UserCreateRequestDto;
import com.example.schedulerproject.user.dto.UserResponseDto;
import com.example.schedulerproject.user.dto.UserUpdateRequestDto;
import com.example.schedulerproject.user.entity.User;
import com.example.schedulerproject.user.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/*
    HTTP 요청을 받아 처리하는 클래스
    - 요청 데이터를 DTO로 받아 서비스에 전달하고, 응답 DTO를 클라이언트에 반환
*/
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor // 생성자 주입을 위한 어노테이션
public class UserController {

    private final UserService userService;

    // 로그인
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody @Valid LoginRequestDto dto, HttpServletRequest request) {
        userService.login(dto, request);
        return ResponseEntity.ok("로그인되었습니다.");
    }

    // 로그아웃
    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return ResponseEntity.ok("로그아웃되었습니다.");
    }

    // 전체 유저 조회
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    // ID로 유저 조회
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    // 유저 생성
    @PostMapping
    public ResponseEntity<UserResponseDto> createUser(@RequestBody @Valid UserCreateRequestDto dto) {
        User createdUser = userService.createdUser(dto);
        return ResponseEntity.ok(new UserResponseDto(createdUser));
    }

    // 유저 수정
    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDto> updateUser(@PathVariable Long id, @RequestBody @Valid UserUpdateRequestDto dto) {
        User updatedUser = userService.updateUser(id, dto);
        return ResponseEntity.ok(new UserResponseDto(updatedUser));
    }

    // 유저 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}