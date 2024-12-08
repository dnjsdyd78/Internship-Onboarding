package com.example.Internship;

import com.example.Internship.auth.dto.request.SigninRequest;
import com.example.Internship.auth.dto.request.SignupRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    final String USERNAME = "exampleUser";
    final String PASSWORD = "Example123!";
    final String NICKNAME = "exampleNickname";

    @Test
    public void 회원가입과_로그인_성공() throws Exception {

        // 1. 회원가입
        SignupRequest signupRequest = new SignupRequest(USERNAME, PASSWORD, NICKNAME);
        mockMvc.perform(post("/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(signupRequest))
                        .with(SecurityMockMvcRequestPostProcessors.csrf()))
                .andExpect(status().isOk());

        // 2. 로그인
        SigninRequest signinRequest = new SigninRequest(USERNAME, PASSWORD);
        MvcResult mvcResult = mockMvc.perform(post("/signin")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(signinRequest))
                        .with(SecurityMockMvcRequestPostProcessors.csrf()))
                .andExpect(status().isOk())
                .andReturn();

        // 로그인 후 Bearer Token 추출
        String bearerToken = mvcResult.getResponse().getContentAsString();
        assertThat(bearerToken).contains("Bearer "); // 토큰이 포함되어 있는지 확인

        // 3. /test 엔드포인트 호출 (헤더에 Authorization 추가)
        mockMvc.perform(post("/test")
                        .header("Authorization", "Bearer " + bearerToken))
                .andExpect(status().isOk());
    }

    @Test
    public void 회원가입_중복_닉네임_실패() throws Exception {
        // 회원가입
        SignupRequest signupRequest = new SignupRequest(USERNAME, PASSWORD, NICKNAME);
        mockMvc.perform(post("/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(signupRequest))
                        .with(SecurityMockMvcRequestPostProcessors.csrf()))
                .andExpect(status().isOk());

        // 중복된 닉네임으로 다시 회원가입 시도
        SignupRequest duplicateSignupRequest = new SignupRequest(USERNAME, PASSWORD, NICKNAME);
        mockMvc.perform(post("/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(duplicateSignupRequest))
                        .with(SecurityMockMvcRequestPostProcessors.csrf()))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void 로그인_실패_잘못된_비밀번호() throws Exception {

        // 잘못된 비밀번호로 로그인 시도
        SigninRequest signinRequest = new SigninRequest(USERNAME, "WrongPassword123!");
        mockMvc.perform(post("/signin")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(signinRequest))
                        .with(SecurityMockMvcRequestPostProcessors.csrf()))
                .andExpect(status().isBadRequest());
    }
}
