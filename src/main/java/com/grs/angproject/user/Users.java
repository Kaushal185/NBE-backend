package com.grs.angproject.user;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity //(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class Users {

    @Id
    private String userId;  // Assuming user_id is a string
    private String firstName;
    private String lastName;
    private String status;
    private String password;

    public String getUserId() {
        return this.userId;
    }

    public String getPassword() {
        return this.password;
    }
}
