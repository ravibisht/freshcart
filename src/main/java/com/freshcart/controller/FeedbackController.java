package com.freshcart.controller;

import com.freshcart.model.Feedback;
import com.freshcart.service.FeedbackServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.Date;


@Controller
public class FeedbackController {

    @Autowired
    private FeedbackServices fs;

    @RequestMapping(value = "/feedback")
    public String feedbackForm(Model model) {

        model.addAttribute("navName", "Back to Home");
        model.addAttribute("navUrl", "/user/home");

        model.addAttribute("title", "Feedback");
        model.addAttribute("active_feedback", "active-item");
        return "user/Feedback";
    }


    @RequestMapping(value = "/feedback", method = RequestMethod.POST)
    public String feedback(Feedback feedback, HttpSession session, Model model) {

        if (session.getAttribute("loggedInUserId") != null) {
            int loggedInUserId = (int) session.getAttribute("loggedInUserId");
            feedback.setUserId(loggedInUserId);
        }

        feedback.setContactDate(new Date());
        fs.saveFeedback(feedback);
        model.addAttribute("status", "true");
        return "user/feedback";
    }


    @RequestMapping(value = "/aboutus")
    public String aboutUs(Model model) {
        model.addAttribute("active_about", "active-item");
        return "user/AboutUs";
    }

}
