package org.example.framework.security;

import lombok.AllArgsConstructor;
import lombok.Value;

@AllArgsConstructor
@Value
public class Authentication {
    private final String login;
}