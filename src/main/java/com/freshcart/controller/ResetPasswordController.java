package com.freshcart.controller;


import com.freshcart.model.User;
import com.freshcart.security.SecurePassword;
import com.freshcart.service.EmailServices;
import com.freshcart.service.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.mail.MessagingException;
import java.util.UUID;

@Controller
public class ResetPasswordController {

    @Autowired
    private UserServices us;

    @Autowired
    private EmailServices es;


    @RequestMapping(value = "forgot", method = RequestMethod.GET)
    public String displayForgotPasswordPage(Model model) {

        model.addAttribute("navName", "Back to Login");
        model.addAttribute("navUrl", "/login");
        model.addAttribute("title", "Forgot Password");
        return "user/ForgotPasswordForm";
    }


    @RequestMapping(value = "/forgot", method = RequestMethod.POST)
    public String forgotPasswordForm(@RequestParam("email") String email, Model model) throws MessagingException {

        User user = us.findByEmail(email);

        if (user == null) {
            model.addAttribute("errorMessage", "User not Present With This Email");
            model.addAttribute("bootstrapclass", "alert alert-danger");
        } else {

            user.setResetToken(UUID.randomUUID().toString());
            us.saveUser(user);
            es.sendEmail(user);

            model.addAttribute("successMessage", "A Password reset link has been sent to " + user.getEmail());
            model.addAttribute("bootstrapclass", "alert alert-success");
        }

        model.addAttribute("navName", "Back to Login");
        model.addAttribute("navUrl", "/login");

        return "user/ForgotPasswordForm";
    }


    @RequestMapping(value = "/reset", method = RequestMethod.GET)
    public String displayResetPasswordPage(@RequestParam("reset_token") String reset_token, Model model) {
        User user = us.findByResetToken(reset_token);

        if (user != null) {

            model.addAttribute("resetToken", reset_token);
        } else {
            model.addAttribute("errorMessage", "Oops! This is an invalid password reset link.");
            model.addAttribute("bootstrapclass", "alert alert-danger");
        }
        model.addAttribute("navName", "Back to Login");
        model.addAttribute("navUrl", "/login");
        model.addAttribute("title", "Reset Password Form");
        return "user/ResetPasswordForm";
    }


    @RequestMapping(value = "/reset", method = RequestMethod.POST)
    public String setNewPassword(@RequestParam("reset_token") String reset_token, @RequestParam("password") String password,
                                 Model model) {

        User resetuser = us.findByResetToken(reset_token);
        SecurePassword sq = new SecurePassword();

        if (resetuser != null) {
            String securePassword = sq.generateSecurePassword(password);
            resetuser.setPassword(securePassword);
            resetuser.setResetToken(null);

            us.saveUser(resetuser);

            model.addAttribute("statusMessage", "You have successfully reset your password.  You may  login now.");
            model.addAttribute("status", true);
            return "user/Login";

        } else {
            model.addAttribute("errorMessage", "Oops!  This is an invalid password reset link.");
            model.addAttribute("navName", "Back to Login");
            model.addAttribute("navUrl", "/login");

            return "error/404";
        }


    }

}