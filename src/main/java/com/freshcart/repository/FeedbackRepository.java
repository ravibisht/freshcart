package com.freshcart.repository;

import java.util.Date;
import java.util.List;

import com.freshcart.model.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Integer> {

     List<Feedback> findByContactDateBetween(Date fromDate, Date toDate);

}

