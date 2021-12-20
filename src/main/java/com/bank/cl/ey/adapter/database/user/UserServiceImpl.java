package com.bank.cl.ey.adapter.database.user;

import com.bank.cl.ey.adapter.database.repository.UserRepository;
import com.bank.cl.ey.domain.model.entity.User;
import com.bank.cl.ey.domain.port.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    @Override
    public User findUserByEmail(String email) {
        return userRepository.findUserByUsername(email);
    }

    public User save(User user) {
        return userRepository.save(user);
    }
}
