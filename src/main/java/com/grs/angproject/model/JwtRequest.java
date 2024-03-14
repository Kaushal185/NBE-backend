package com.grs.angproject.model;

import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JwtRequest {
    String username;
    String password;

    public String getUsername() {
        return this.username;
    }
    public String getPassword() {
        return this.password;
    }
}
