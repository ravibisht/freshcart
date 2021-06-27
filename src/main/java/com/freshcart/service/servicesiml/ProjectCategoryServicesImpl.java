package com.freshcart.service.servicesiml;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.GYS.app.model.ProjectCategory;
import com.GYS.app.repository.ProjectCategoryRepository;


@Service
public class ProjectCategoryServicesImpl implements ProjectCategoryServices {

@Autowired
private ProjectCategoryRepository pcr;	

@Autowired
private ImageAndVideoServices imgAndVideoService;

	
public List<ProjectCategory> allCategories() {	
	
	List<ProjectCategory> pc=pcr.findAll();
	
	return pc;
  }


@Override
public ProjectCategory findById(int id) {
    	
    return pcr.getOne(id);
}


@Override
public void saveProjectCategory(ProjectCategory projectCategory) {
   
	pcr.save(projectCategory);
}


@Override
public void saveProjectCategoryWithImage(ProjectCategory projectCategory, MultipartFile image) {

	try {
		String imagePath=imgAndVideoService.saveProjectCategoryImage(image);
		projectCategory.setImage(imagePath);
		}
		catch(Exception e) {e.getMessage();}
	
	    pcr.save(projectCategory);

}

@Override
public void deleteProjectCategoryById(int id) {
  
	imgAndVideoService.deleteProjectCategoryImage(pcr.findById(id).getImage());
    pcr.deleteById(id);
}


@Override
public Long countAllProjectCategories() {
	
	return (Long)pcr.count();
}


@Override
public Page<ProjectCategory> showProjectCategoryByPage(Pageable pageable) {

	return pcr.findAll(pageable);
}



}
