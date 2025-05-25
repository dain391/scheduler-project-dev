package com.example.schedulerproject.user.service;

import com.example.schedulerproject.user.dto.LoginRequestDto;
import com.example.schedulerproject.user.dto.UserCreateRequestDto;
import com.example.schedulerproject.user.dto.UserUpdateRequestDto;
import com.example.schedulerproject.user.entity.User;
import com.example.schedulerproject.user.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/*
    비즈니스 로직을 담당하는 클래스
    - 사용자 등록/조회/수정/삭제 등의 작업 수행
*/
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    // 로그인
    public void login(LoginRequestDto dto, HttpServletRequest request) {
        User user = userRepository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("이메일 또는 비밀번호가 일치하지 않습니다."));

        if (!user.getPassword().equals(dto.getPassword())) {
            throw new IllegalArgumentException("이메일 또는 비밀번호가 일지하지 않습니다.");
        }

        HttpSession session = request.getSession();
        session.setAttribute("userId", user.getId());
    }

    // 전체 유저 조회
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // ID로 유저 조회
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 유저가 존재하지 않습니다. id=" + id));
    }

    // 유저 생성(DTO를 받아서 엔티티로 변환 후 저장)
    public User createdUser(UserCreateRequestDto dto) {
        User user = User.builder()
                .name(dto.getName())
                .password(dto.getPassword())
                .email(dto.getEmail())
                .build();
        return userRepository.save(user);
    }

    // 유저 수정(DTO로 받은 데이터로 기존 엔티티 업데이트 후 저장)
    public User updateUser(Long id, UserUpdateRequestDto dto) {
        User user = getUserById(id);
        user.setName(dto.getName());
        user.setPassword(dto.getPassword());
        user.setEmail(dto.getEmail());
        return userRepository.save(user);
    }

    // 유저 삭제
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}