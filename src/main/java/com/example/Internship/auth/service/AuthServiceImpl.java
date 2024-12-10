package com.example.Internship.auth.service;

import com.example.Internship.auth.dto.request.SigninRequest;
import com.example.Internship.auth.dto.request.SignupRequest;
import com.example.Internship.auth.dto.response.SignupResponse;
import com.example.Internship.common.config.JwtUtil;
import com.example.Internship.common.exception.ApiException;
import com.example.Internship.domain.apipayload.status.ErrorStatus;
import com.example.Internship.domain.user.entity.User;
import com.example.Internship.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.example.Internship.domain.user.enums.UserRole.ROLE_USER;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    /**
     * @param signupRequest 회원가입 양식(이름, 비밀번호, 닉네임)
     * @return dto(이름, 닉네임, 권한)
     */
    @Transactional
    public SignupResponse signup(SignupRequest signupRequest) {

        // 사용자 아이디 중복 검증
        if (userRepository.existsByNickname(signupRequest.getNickname())) {
            throw new ApiException(ErrorStatus._EXIST_USERNAME);
        }

        // 닉네임 중복 검증
        if (userRepository.existsByNickname(signupRequest.getNickname())) {
            throw new ApiException(ErrorStatus._EXIST_NICKNAME);
        }

        // 비밀번호 암호화
        String encodedPassword = passwordEncoder.encode(signupRequest.getPassword());

        User newUser = new User(
                signupRequest.getUsername(),
                encodedPassword,
                signupRequest.getNickname(),
                ROLE_USER
        );

        User savedUser = userRepository.save(newUser);
        return new SignupResponse(savedUser.getUsername(), savedUser.getNickname(), savedUser.getRole());
    }

    /**
     * 로그인
     *
     * @param signinRequest 로그인 시 필요한 json body ( 이름, 비밀번호 )
     * @return 로그인 후 발급되는 토큰 반환 ( "Bearer eyJ~" )
     */
    public String signin(SigninRequest signinRequest) {

        User user = userRepository.findByUsername(signinRequest.getUsername()).orElseThrow(
                () -> new ApiException(ErrorStatus._SIGN_IN_ERROR)
        );

        // 비밀번호 검증
        if (!passwordEncoder.matches(signinRequest.getPassword(), user.getPassword())) {
            throw new ApiException(ErrorStatus._SIGN_IN_ERROR);
        }

        String token = jwtUtil.createToken(user.getId(), user.getUsername(), user.getNickname(), user.getRole());

        return jwtUtil.substringToken(token);
    }
}
