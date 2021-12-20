package com.bank.cl.ey.domain.model.entity;

import com.bank.cl.ey.domain.model.entity.enums.AppUserRole;
import com.bank.cl.ey.domain.model.entity.enums.UserState;
import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @Column(name = "id", unique = true)
    private String userId;
    @Column(unique = true, nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(name = "state", length = 20)
    @Enumerated(EnumType.STRING)
    private UserState status;

    @Column(name = "token")
    private String token;

    @Column(name = "name")
    private String name;

    @Column(name = "created")
    private LocalDateTime created;

    @Column(name = "modified")
    private LocalDateTime modified;

    @Column(name = "last_login")
    private LocalDateTime lastLogin;

    @ElementCollection(fetch = FetchType.EAGER)
    List<AppUserRole> appUserRoles;

    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Phone> phoneList = new ArrayList<>();

    @PrePersist
    protected void onCreate() {
        setUserId(java.util.UUID.randomUUID().toString());
    }
}
