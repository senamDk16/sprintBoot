package com.senam.user.repository;

import com.senam.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
//    User findByUsername(String username);
//    User findByEmail(String email);
//    User findByEmailAndUsername(String username, String email);
//    @Query("SELECT * FROM users WHERE users.email = :X")
//    User findUserByEmail(@Param("X") String email);

    User findByUuid(UUID uuid);

}
