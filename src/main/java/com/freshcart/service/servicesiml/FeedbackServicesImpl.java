package com.freshcart.service.servicesiml;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.GYS.app.model.Feedback;
import com.GYS.app.repository.FeedbackRepository;

@Service
public class FeedbackServicesImpl implements FeedbackServices {

@Autowired
private FeedbackRepository fr;
	
	@Override
	public void saveFeedback(Feedback feedback) {
        
		fr.save(feedback);
	}

	@Override
	public Feedback getFeedback(int id) {
              
		return fr.getOne(id);
	}

	@Override
	public List<Feedback> getFeedbacks() {

		return fr.findAll();
	}

	@Override
	public void deleteFeedbackById(int id) {
      
		fr.deleteById(id);
	}

	@Override
	public Feedback getfeedback(int id) {
     
		return fr.getOne(id);
	}

	@Override
	public Page<Feedback> showFeedbackByPage(Pageable pageable) {
		
		return fr.findAll(pageable);
	}

	@Override
	public Long countAllFeedbacks() {

		return fr.count();
	}
   
   @Override
    public List<Feedback> findFeedbackByFromDateToDate(Date fromDate, Date toDate) {

		return fr.findByContactDateBetween(fromDate, toDate);
	}

}
