package com.freshcart.service;

import com.freshcart.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

 public interface UserServices {


     void saveUser(User user);

     void saveUserWithProfileImage(User user, MultipartFile image);

     boolean UserExitsByUsername(String username);

     boolean UserExitsByEmail(String email);

     User findByUsername(String username);

     User getUserById(int id);

     User findByUser(String username);

     List<User> getAllUser();

     User findByResetToken(String reset_token);

     User findByEmail(String email);

     void userDeleteById(int id);

     Page<User> showUsersByPage(Pageable pageable);

     Long countAllUsers();

     List<User> findUserBetweenFromDateToDate(Date fromDate, Date toDate);


}
