package com.mrvicari.winstondebts.controller.controller;

import com.mrvicari.winstondebts.controller.AccountController;
import com.mrvicari.winstondebts.exception.AccountAlreadyExistException;
import com.mrvicari.winstondebts.model.Account;
import com.mrvicari.winstondebts.service.AccountService;
import org.junit.Before;
import org.junit.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private AccountService accountService;

    private String accountDetails = "{\"username\": \"test\", \"password\": \"test\"}";

    @InjectMocks
    private AccountController controller;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }


    @Test
    public void createAccount_GivenAccountDetails_Return201() throws Exception {
        Account account = new Account("Test", "Test");

        when(accountService.signUp(any())).thenReturn(account);

        mockMvc
                .perform(post("/account/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .content(accountDetails))
                .andExpect(status().isCreated());
    }

    @Test
    public void createAccount_GivenAccountDetails_ReturnException() throws Exception {
        Account account = new Account("Test", "Test");

        when(accountService.signUp(any())).thenThrow(new AccountAlreadyExistException());

        mockMvc
                .perform(post("/account/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(accountDetails))
                .andExpect(status().isBadRequest());
    }
}
