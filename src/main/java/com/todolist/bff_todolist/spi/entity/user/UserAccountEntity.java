package com.todolist.bff_todolist.spi.entity.user;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "USER_ACCOUNT")
@Getter
@Setter
public class UserAccountEntity {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private UUID id;

    @Column(nullable = false, unique = true, name = "USERNAME")
    private String username;

    @Column(nullable = false, name = "PASSWORD")
    private String password;

    @Column(name = "ACCOUNT_DUE_TIME")
    private Instant accountDueTime;

    @Column(name = "ACCOUNT_NON_LOCKED")
    private boolean accountNonLocked;

    @Column(name = "CREDENTIALS_DUE_TIME")
    private Instant credentialsDueTime;

    @Column(nullable = false, name = "ENABLED")
    private boolean enabled;

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "USER_ROLES", joinColumns = @JoinColumn(name = "USER_ID"))
    @Column(name = "ROLES")
    private List<String> roles;

    @Column(nullable = false, unique = true)
    private String email;
}
