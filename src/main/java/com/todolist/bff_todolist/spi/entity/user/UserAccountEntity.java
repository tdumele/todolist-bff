package com.todolist.bff_todolist.spi.entity.user;

import com.todolist.bff_todolist.domain.model.user.Role;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "USER_ACCOUNT")
@Getter
@Setter
public class UserAccountEntity {

    @Id
    @GeneratedValue
    @Column(name = "USER_ID")
    private UUID id;

    @Column(nullable = false, unique = true, name = "USER_USERNAME")
    private String username;

    @Column(nullable = false, name = "USER_PASSWORD")
    private String password;

    @Column(name = "USER_ACCOUNT_DUE_TIME")
    private Instant accountDueTime;

    @Column(name = "USER_ACCOUNT_NON_LOCKED")
    private boolean accountNonLocked;

    @Column(name = "USER_CREDENTIALS_DUE_TIME")
    private Instant credentialsDueTime;

    @Column(nullable = false, name = "USER_ENABLED")
    private boolean enabled;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "USER_ROLES", joinColumns = @JoinColumn(name = "USER_ID"))
    @Column(name = "USER_ROLE")
    @Enumerated(EnumType.STRING)
    private Set<Role> roles = new HashSet<>();

    @Column(nullable = false, unique = true, name = "USER_EMAIL")
    private String email;

    @CreationTimestamp
    @Column(updatable = false, name = "USER_CREATED_AT")
    private Instant createdAt;

    @UpdateTimestamp
    @Column(name = "USER_UPDATED_AT")
    private Instant updatedAt;
}
