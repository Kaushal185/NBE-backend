package com.grs.angproject.user;

import com.grs.angproject.email.EmailService;
import com.grs.angproject.email.PasswordResetToken;
import com.grs.angproject.email.PasswordResetTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordResetTokenRepository passwordResetTokenRepository;

    @Autowired
    private EmailService emailService;

    @Override
    public Optional<User> findByUserId(String userId) {
        return userRepository.findByUserId(userId);
    }

    @Override
    public User registerUser(User user) {
        if (userRepository.findByUserId(user.getUserId()).isPresent()) {
            throw new IllegalArgumentException("User already exists");
        }
        userRepository.save(user);
        return user;
    }

    @Override
    public void generateAndSendOtp(String userId) {
        Optional<User> userOptional = userRepository.findByUserId(userId);
        if (userOptional.isEmpty()) {
            throw new IllegalArgumentException("User not found");
        }

        User user = userOptional.get();

        // Generate OTP
        String otp = generateOTP();

        // Save OTP token
        System.out.println("start of it");
        PasswordResetToken token = new PasswordResetToken();

        token.setUserId(userId);
        token.setOtp(otp);
        token.setExpiryTime(LocalDateTime.now().plusMinutes(10));

        System.out.println("in the middle");
        System.out.println(token.getId());
        System.out.println(token.getUserId());
        System.out.println(token.getExpiryTime());
        System.out.println(token.getOtp());
        passwordResetTokenRepository.save(token);
        System.out.println("end of it");
        System.out.println(user.getEmail());
        // Send email with OTP
        emailService.sendEmail(user.getEmail(), "Password Reset OTP", "Your OTP is: " + otp);
        System.out.println("second end of it");
    }

    private String generateOTP() {
        Random random = new Random();
        int otpNumber = random.nextInt(999999);
        return String.format("%06d", otpNumber); // Format OTP as 6-digit number
    }


    @Override
    public boolean verifyOtp(String userId, String otp) {
        PasswordResetToken token = passwordResetTokenRepository.findByUserId(userId);
        if (token == null || token.getExpiryTime().isBefore(LocalDateTime.now()) || !token.getOtp().equals(otp)) {
            return false;
        }
        return true;
    }

    @Override
    public void resetPassword(String userId, String newPassword) {
        Optional<User> userOptional = userRepository.findByUserId(userId);
        if (!userOptional.isPresent()) {
            throw new IllegalArgumentException("User not found");
        }

        User user = userOptional.get();
        user.setPassword(newPassword);
        userRepository.save(user);

        PasswordResetToken token = passwordResetTokenRepository.findByUserId(userId);
        if (token != null) {
            passwordResetTokenRepository.delete(token);
        }
    }
}
