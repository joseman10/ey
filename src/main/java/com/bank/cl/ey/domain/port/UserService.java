package com.bank.cl.ey.domain.port;

import com.bank.cl.ey.domain.model.entity.User;

public interface UserService {
    User findUserByEmail(String email);
    User save(User user);
}
