package com.mrvicari.winstondebts.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Role")
public class Role {

    @Id
    @Column(name = "role_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer roleId;

    @OneToOne
    @JoinColumn(name = "username")
    private Account account;

    @Column(name = "authority", length = 100)
    private String authority;

    public Role(Account account, String authority) {
        this.account = account;
        this.authority = authority;
    }
}


