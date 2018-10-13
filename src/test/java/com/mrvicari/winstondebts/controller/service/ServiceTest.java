package com.mrvicari.winstondebts.controller.service;

import com.mrvicari.winstondebts.exception.AccountAlreadyExistException;
import com.mrvicari.winstondebts.model.Account;
import com.mrvicari.winstondebts.model.Role;
import com.mrvicari.winstondebts.repository.AccountRepository;
import com.mrvicari.winstondebts.repository.RoleRepository;
import com.mrvicari.winstondebts.service.AccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.Silent.class)
public class ServiceTest {

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private RoleRepository roleRepository;

    @InjectMocks
    private AccountService accountService;

    private Account account = new Account("Test", "Test");


    @Test(expected = AccountAlreadyExistException.class)
    public void signUp_ThrowException() {
        when(accountRepository.findByUsername(anyString())).thenReturn(account);

        accountService.signUp(account);
    }

    @Test
    public void signUp_ReturnAccount() {
        String password = new BCryptPasswordEncoder().encode(account.getPassword());
        Account result = new Account("Test", password, null);
        Role role = new Role(result, "ROLE_USER");

        when(accountRepository.findByUsername(anyString())).thenReturn(null);
        when(accountRepository.save(any())).thenReturn(result);
        when(roleRepository.save(any())).thenReturn(role);

        assertThat(accountService.signUp(account)).isEqualTo(result);
    }
}
