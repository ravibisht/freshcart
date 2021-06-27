package com.freshcart.service.servicesiml;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import com.GYS.app.model.Project;
import com.GYS.app.repository.ProjectRepository;
import com.GYS.app.services.ImageAndVideoServices;

@Service
public class ProjectServicesImpl implements ProjectServices {

	@Autowired
	private ProjectRepository repo;
	@Autowired
	private ImageAndVideoServices imgAndVideoService;
	
	@Override
	public void saveProject(Project project) {
		
		repo.save(project);
		
	}

	@Override
	public void saveProjectWithMedia(Project project, MultipartFile[] images, MultipartFile video) {

		try {
			 String imagesPath=imgAndVideoService.saveProjectImage(images);
		     String videoPath=imgAndVideoService.saveProjectVideo(video);
		     project.setImage(imagesPath);
		     project.setVideo(videoPath);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		repo.save(project);
	 }
	
	
	public List<Project> getAllProject() {
         
		return repo.findAll(Sort.by(Sort.Direction.DESC, "id"));
	}

	@Override
	public Project getProject(int projectId) {
		
		return repo.getOne(projectId);
	}

	
	public void deleteProjectById(int project_id) {
       
		Project project=repo.getOne(project_id);
		imgAndVideoService.deleteProjectImages(project.getImage());
		imgAndVideoService.deleteProjectVideo(project.getVideo());
		repo.deleteById(project_id);
	}

	@Override
	public Page<Project> showProjectByPage(Pageable pageable) {

		return repo.findAll(pageable);
	}

	@Override
	public Long countAllProject() {
		return (Long)repo.count();
	}

	@Override
	public List<Project> getLatest4Project() {
		
		return repo.findTop4ByOrderByIdDesc();
	}

	
	

}
