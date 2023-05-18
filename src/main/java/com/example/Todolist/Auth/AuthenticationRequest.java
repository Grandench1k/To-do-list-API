package com.example.Todolist.Auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationRequest {
    @Email(message = "Invalid email address.")
    @NotEmpty(message = "Email is empty.")
    private String email;
    @NotEmpty(message = "Password is empty.")
    private String password;
}
