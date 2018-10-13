package com.mrvicari.winstondebts.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "account")
public class Account {

    @Id
    private String username;

    @NotNull
    @JsonIgnore
    private String password;

    private Boolean isEnabled;

    @OneToOne(mappedBy = "account", cascade = CascadeType.ALL)
    private Role accountRole;

    public Account() {
    }

    public Account(String username, @NotNull String password) {
        this.username = username;
        this.password = password;
        this.isEnabled = true;
    }

    public Account(String username, @NotNull String password, Role accountRole) {
        this.username = username;
        this.password = password;
        this.isEnabled = true;
        this.accountRole = accountRole;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
