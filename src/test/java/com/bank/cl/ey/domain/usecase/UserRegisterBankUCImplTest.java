package com.bank.cl.ey.domain.usecase;

import com.bank.cl.ey.adapter.controller.model.Phones;
import com.bank.cl.ey.adapter.controller.model.inbound.UserInfo;
import com.bank.cl.ey.adapter.controller.model.outbound.UserDTO;
import com.bank.cl.ey.config.jwt.JwtTokenProvider;
import com.bank.cl.ey.domain.model.entity.User;
import com.bank.cl.ey.domain.model.entity.enums.UserState;
import com.bank.cl.ey.domain.port.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DisplayName("Uses case register user")
class UserRegisterBankUCImplTest {
    private UserRegisterBankUCImpl userRegisterBankUC;
    @Mock
    private UserService userService;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private JwtTokenProvider jwtTokenProvider;
    @Mock
    private AuthenticationManager authenticationManager;

    @BeforeEach
    void setUp() {
        userRegisterBankUC = new UserRegisterBankUCImpl(userService,
                passwordEncoder, jwtTokenProvider, authenticationManager);
    }

    @Test
    @DisplayName("When Register user, then Return Data.")
    void whenUserRegister() {
        String email = "micorreo@domain.cl";
        LocalDateTime now = LocalDateTime.now();
        User userEntity = new User();
        userEntity.setUserId("uuid123");
        userEntity.setUsername(email);
        userEntity.setStatus(UserState.ACTIVE);
        userEntity.setToken("eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJob2xhMkBkb21haW4uY2wiL");
        userEntity.setCreated(now);
        userEntity.setModified(now);
        userEntity.setLastLogin(now);
        UserInfo userInfo = new UserInfo();
        userInfo.setEmail(email);
        userInfo.setName("Ey Perez");
        userInfo.setPassword("Pass12");
        Phones phones = new Phones();
        phones.setCitycode("123");
        phones.setContrycode("051");
        phones.setNumber("1234566");
        userInfo.setPhones(Arrays.asList(
                phones
        ));
        when(userService.save(any())).thenReturn(userEntity);
        UserDTO response = userRegisterBankUC.register(userInfo);
        Assertions.assertEquals(userEntity.getUsername(), response.getEmail());
    }


}