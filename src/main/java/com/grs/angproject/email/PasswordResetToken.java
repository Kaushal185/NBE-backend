package com.grs.angproject.email;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "PASSWORD_RESET_TOKENS")
public class PasswordResetToken {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "password_reset_tokens_seq")
    @SequenceGenerator(name = "password_reset_tokens_seq", sequenceName = "RMWB.password_reset_tokens_seq", allocationSize = 1)
    @Column(name = "ID")
    private Long id;

    @Column(name = "USER_ID")
    private String userId;

    @Column(name = "OTP")
    private String otp;

    @Column(name = "EXPIRY_TIME")
    private LocalDateTime expiryTime;

    // Constructors (default and parameterized), getters, setters

    // Default constructor
    public PasswordResetToken() {
    }

    // Parameterized constructor
    public PasswordResetToken(String userId, String otp, LocalDateTime expiryTime) {
        this.userId = userId;
        this.otp = otp;
        this.expiryTime = expiryTime;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public LocalDateTime getExpiryTime() {
        return expiryTime;
    }

    public void setExpiryTime(LocalDateTime expiryTime) {
        this.expiryTime = expiryTime;
    }
}
