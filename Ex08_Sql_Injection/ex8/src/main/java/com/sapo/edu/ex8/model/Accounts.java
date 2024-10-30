package com.sapo.edu.ex8.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class Accounts {
    int accountId;
    String username;
    String password;

    @Override
    public String toString() {
        return accountId + " - " + username + " - " + password;
    }
}
