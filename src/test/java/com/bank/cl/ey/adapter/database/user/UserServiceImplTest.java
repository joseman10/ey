package com.bank.cl.ey.adapter.database.user;

import com.bank.cl.ey.adapter.controller.model.Phones;
import com.bank.cl.ey.adapter.database.repository.UserRepository;
import com.bank.cl.ey.domain.model.entity.Phone;
import com.bank.cl.ey.domain.model.entity.User;
import com.bank.cl.ey.domain.model.entity.enums.UserState;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DisplayName("Uses service to UserService")
class UserServiceImplTest {
    private UserServiceImpl userService;
    @Mock
    private UserRepository userRepository;
    @BeforeEach
    void setUp() {
        userService = new UserServiceImpl(userRepository);
    }

    @Test
    @DisplayName("When Find by username, then Return Data.")
    void whenFindByUsernameReturnData() {
        String email = "micorreo@domain.cl";
        LocalDateTime now = LocalDateTime.now();
        User user = new User();
        user.setUserId("uuid123");
        user.setUsername(email);
        user.setStatus(UserState.ACTIVE);
        user.setToken("eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJob2xhMkBkb21haW4uY2wiL");
        user.setCreated(now);
        user.setModified(now);
        user.setLastLogin(now);

        user.setPhoneList(Arrays.asList(
                Phone.builder()
                        .contrycode("051")
                        .number("1234566")
                        .citycode("123")
                        .build()
        ));
        when(userRepository.findUserByUsername(anyString())).thenReturn(user);
        User userResponse = userService.findUserByEmail(email);

        Assertions.assertEquals(email, userResponse.getUsername());
    }

}