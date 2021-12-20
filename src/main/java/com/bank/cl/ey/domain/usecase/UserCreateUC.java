package com.bank.cl.ey.domain.usecase;

import com.bank.cl.ey.adapter.controller.model.inbound.UserInfo;
import com.bank.cl.ey.adapter.controller.model.outbound.UserDTO;

public interface UserCreateUC {

    UserDTO register(UserInfo userInfo);
}
