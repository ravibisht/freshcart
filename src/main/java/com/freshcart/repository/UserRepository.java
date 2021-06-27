package com.freshcart.repository;

import java.util.Date;
import java.util.List;

import com.freshcart.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface UserRepository extends JpaRepository<User, Integer> {


    User findByUsername(String username);

    User findByEmail(String email);

    User findById(int id);

    @Query("from User where reset_token=?1")
    User findByReset_token(String reset_token);

    List<User> findByCreatedDateBetween(Date fromDate, Date toDate);

}
