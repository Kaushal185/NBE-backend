package com.grs.angproject.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface UserRepository extends JpaRepository<Users, String> {
    Optional<Users> findByUserId(String username); // this line is casuing error

}
