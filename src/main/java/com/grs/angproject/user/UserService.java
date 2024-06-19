package com.grs.angproject.user;

import java.util.Optional;

public interface UserService {
    Optional<User> findByUserId(String userId);
    User registerUser(User user);
    void resetPassword(String userId, String newPassword);
    void generateAndSendOtp(String userId);
    boolean verifyOtp(String userId, String otp);
}
