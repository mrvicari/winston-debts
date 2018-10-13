package com.mrvicari.winstondebts.controller;

import com.mrvicari.winstondebts.exception.AccountAlreadyExistException;
import com.mrvicari.winstondebts.model.Account;
import com.mrvicari.winstondebts.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping(value = "/signup", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void signUpUser(@RequestBody Account account) {
        accountService.signUp(account);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public void accountAlreadyExistException(AccountAlreadyExistException ex) {
        // Exception Handler
    }


}
