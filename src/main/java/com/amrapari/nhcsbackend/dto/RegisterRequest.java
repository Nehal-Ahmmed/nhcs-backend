package com.amrapari.nhcsbackend.dto;

import com.amrapari.nhcsbackend.domain.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private String username;
    private String email;
    private String password;
    private Role role;
    
    // Additional fields for Patient or Doctor if needed, simplified for now
    private String fullName;
}
