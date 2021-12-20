package com.bank.cl.ey.adapter.controller.model.inbound;

import com.bank.cl.ey.adapter.controller.model.Phones;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class UserInfo {
    private String name;
    private String email;
    private String password;
    private List<Phones> phones;
}
