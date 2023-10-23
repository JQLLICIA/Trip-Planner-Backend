package com.example.tripplanner.repository;

import com.example.tripplanner.entity.UserEntity;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.ListCrudRepository;

public interface UserRepository extends ListCrudRepository<UserEntity, Integer>{

    UserEntity getByUsername(String username);
    UserEntity getByEmail(String email);

    @Modifying
    @Query("UPDATE users SET password = :newPassword WHERE username = :Username")
    void updatePasswordByUsername(String username, String newPassword);

    @Modifying
    @Query("UPDATE users SET email = :newEmail WHERE username = :Username")
    void updateEmailByUsername(String username, String newEmail);
}