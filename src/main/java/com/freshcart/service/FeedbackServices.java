package com.freshcart.service;

import com.freshcart.model.Feedback;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;


public interface FeedbackServices {

    void saveFeedback(Feedback feedback);

    Feedback getFeedback(int id);

    List<Feedback> getFeedbacks();

    Feedback getfeedback(int id);

    void deleteFeedbackById(int id);

    Page<Feedback> showFeedbackByPage(Pageable pageable);

    Long countAllFeedbacks();

    List<Feedback> findFeedbackByFromDateToDate(Date fromDate, Date toDate);

}
