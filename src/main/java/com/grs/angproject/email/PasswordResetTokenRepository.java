package com.grs.angproject.email;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long> {
    PasswordResetToken findByUserId(String userId);
    PasswordResetToken findByOtp(String otp);
}

