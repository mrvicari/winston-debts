package com.mrvicari.winstondebts.service;

import com.mrvicari.winstondebts.exception.AccountAlreadyExistException;
import com.mrvicari.winstondebts.model.Account;
import com.mrvicari.winstondebts.model.Role;
import com.mrvicari.winstondebts.repository.AccountRepository;
import com.mrvicari.winstondebts.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private RoleRepository roleRepository;


    public Account signUp(Account account) {
        Account exist = accountRepository.findByUsername(account.getUsername());
        if (exist != null) {
            throw new AccountAlreadyExistException();
        }
        String password = account.getPassword();
        account.setPassword(passwordEncoder().encode(password));
        account = accountRepository.save(account);
        roleRepository.save(new Role(account, "ROLE_USER"));
        return account;
    }

    private PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
