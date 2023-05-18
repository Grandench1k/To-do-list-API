package com.example.Todolist.Auth;

import com.example.Todolist.User.Role;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
public class RegisterRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Email(message = "Invalid email address.")
    @NotEmpty(message = "Email is empty.")
    private String email;
    @NotEmpty(message = "Name is empty.")
    private String firstname;
    @NotEmpty(message = "Surname is empty.")
    private String lastname;
    @NotEmpty(message = "Password is empty.")
    private String password;
    private Role role;
}
