package com.oose.jobportal.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private String accountnumner;
    private String gmail;
    private String location;
    private String name;
    private String password;
    private String phonenumber;
    private String ROLE;
}
