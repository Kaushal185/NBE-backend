package com.grs.angproject.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users, String> {
    Optional<Users> findByUserId(String userId); // this line is casuing error

}
