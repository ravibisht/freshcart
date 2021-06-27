/**
 *
 */
package com.freshcart.service.servicesiml;


import com.freshcart.model.User;
import com.freshcart.repository.UserRepository;
import com.freshcart.service.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;


@Service
public class UserServicesImpl implements UserServices {

    @Autowired
    private UserRepository userrepo;
    @Autowired
    private ImageAndVideoServices imgAndVideoService;


    @Override
    public void saveUser(User user) {
        userrepo.save(user);
    }

    @Override
    public void saveUserWithProfileImage(User user, MultipartFile imageFile) {

        String imageFileName = "";

        try {

            if (!imageFile.isEmpty()) {
                imageFileName = imgAndVideoService.saveUserProfileImage(imageFile);
                user.setProfilePicture(imageFileName);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        userrepo.save(user);

    }


    public User findByUsername(String username) {

        User user = userrepo.findByUsername(username);

        return user;

    }

    @Override
    public boolean UserExitsByUsername(String username) {


        if (userrepo.findByUsername(username) != null) {

            return true;
        }

        return false;
    }


    @Override
    public boolean UserExitsByEmail(String email) {
        if (userrepo.findByEmail(email) != null) {
            return t                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                          rue;
        }

        return false;
    }


    @Override
    public User getUserById(int id) {


        return userrepo.findById(id);

    }


    @Override
    public User findByUser(String username) {
        User user = userrepo.findByUsername(username);
        return user;
    }


    @Override
    public List<User> getAllUser() {

        return userrepo.findAll();
    }


    @Override
    public User findByResetToken(String reset_token) {
        return userrepo.findByReset_token(reset_token);
    }


    @Override
    public User findByEmail(String email) {

        return userrepo.findByEmail(email);
    }


    @Override
    public void userDeleteById(int id) {
        String imagePath = userrepo.findById(id).getProfilePicture();

        if (!imagePath.equals("default-user.png"))
            imgAndVideoService.deleteUserProfileImage(imagePath);

        userrepo.deleteById(id);
    }


    @Override
    public Long countAllUsers() {

        return (Long) userrepo.count();
    }


    @Override
    public Page<User> showUsersByPage(Pageable pageable) {

        return userrepo.findAll(pageable);

    }


    public List<User> findUserBetweenFromDateToDate(Date fromDate, Date toDate) {


        return userrepo.findByCreatedDateBetween(fromDate, toDate);
    }


}
	
	


