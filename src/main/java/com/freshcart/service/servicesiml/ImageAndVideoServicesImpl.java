package com.freshcart.service.servicesiml;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImageAndVideoServicesImpl implements ImageAndVideoServices{

	
	@Override
      public String saveUserProfileImage(MultipartFile imageFile) throws Exception {
		String userImagePath=System.getProperty("user.dir")+"\\src\\main\\resources\\static\\Users";
	    String imageFileName="default-user.png";		
	   
	     if (!imageFile.isEmpty()) {
	          imageFileName = UUID.randomUUID().toString();
	        
		      imageFileName+=imageFile.getOriginalFilename().substring(
		    		       imageFile.getOriginalFilename().lastIndexOf('.'));
	        
	           
		       Path imagePath=Paths.get(userImagePath,imageFileName);
	           
                  
	               try {
                        imageFile.transferTo(imagePath);
				    	}
			        catch (Exception e) {e.getMessage();}
	          }
				
		return imageFileName;
	}
	
	@Override
	public String saveProjectImage(MultipartFile[] imageFiles) throws Exception {

		String projectImagesPath=System.getProperty("user.dir")+"\\src\\main\\resources\\static\\ProjectAssets\\Images";
	      
	     String imagesFileName="";		
		 String imageFileName="";
	     if(!imageFiles[0].isEmpty()) {
                
	    	  for(MultipartFile imageFile:imageFiles) {
	    		  
	    		  imageFileName = UUID.randomUUID().toString();
	    		  imageFileName+=imageFile.getOriginalFilename().substring(
	    				         imageFile.getOriginalFilename().lastIndexOf('.')); 
	    		  System.out.println("ImageFilename : "+imageFileName);
	   		      Path imagesPath=Paths.get(projectImagesPath,imageFileName);
	   	          System.out.println("ImagePath  : "+imagesPath);
	   	          imageFile.transferTo(imagesPath);
	   	          imagesFileName+=imageFileName+",";
	    	  }
          }		
		
	return imagesFileName;
	}

	
	@Override
	public String saveProjectVideo(MultipartFile videoFile) throws Exception {
		String projectVideoPath=System.getProperty("user.dir")+"\\src\\main\\resources\\static\\ProjectAssets\\Video";
	      
	     String videoFileName="novideo.mp4";		
	   
	   if (!videoFile.isEmpty()) {
		   videoFileName = UUID.randomUUID().toString();
	        
		   videoFileName+=videoFile.getOriginalFilename().substring(
				          videoFile.getOriginalFilename().lastIndexOf('.'));
	        
	       System.out.println("ImageFilename : "+videoFileName);
		   Path videoPath=Paths.get(projectVideoPath,videoFileName);
	       System.out.println("ImagePath  : "+videoPath);
           videoFile.transferTo(videoPath);
			
		}
				
			return videoFileName;
	}
	
	
	
	@Override
	public String saveProjectCategoryImage(MultipartFile imageFile) throws Exception {
			
     String categoryImagePath=System.getProperty("user.dir")+"\\src\\main\\resources\\static\\ProjectAssets\\Category";
      
     String imageFileName="noimage.png";		
   
   if (!imageFile.isEmpty()) {
        imageFileName = UUID.randomUUID().toString();
        
	    imageFileName+=imageFile.getOriginalFilename().substring(
	    		       imageFile.getOriginalFilename().lastIndexOf('.'));
        
       	 Path imagePath=Paths.get(categoryImagePath,imageFileName);
         imageFile.transferTo(imagePath);
      } 
         return imageFileName;
	}
	
	
	@Override
	public boolean deleteUserProfileImage(String imageFile) {
        
	   boolean status=false;
	  
	     String userImagePath=System.getProperty("user.dir")+"\\src\\main\\resources\\static\\Users";
         Path imagePath=Paths.get(userImagePath,imageFile);
         File fileToDelete=imagePath.toFile();
         status=fileToDelete.delete();
	   
       return status;
	}
	
	

	@Override
	public boolean deleteProjectCategoryImage(String imageFile) {
        
	   boolean status=false;
	   if(!imageFile.isEmpty()) {
	   String categoryImagePath=System.getProperty("user.dir")+"\\src\\main\\resources\\static\\ProjectAssets\\Category";
       Path imagePath=Paths.get(categoryImagePath,imageFile);
       File fileToDelete=imagePath.toFile();
       status=fileToDelete.delete();
	   }
       return status;
	}


	@Override
	public boolean deleteProjectImages(String imageFile) {
		   boolean status=false;

      if(!imageFile.isEmpty()) {
	   String []imagesFiles=imageFile.split(",");
	   String projectImagesPath=System.getProperty("user.dir")+"\\src\\main\\resources\\static\\ProjectAssets\\Images";
       for(String image:imagesFiles) {
	   Path imagePath=Paths.get(projectImagesPath,image);
       File fileToDelete=imagePath.toFile();
       status=fileToDelete.delete();
	  }
     }   
       return status;
	}
	
	
	@Override
	public boolean deleteProjectVideo(String videoFile) {
        
	   boolean status=false;
	   if(!videoFile.isEmpty()) {
	   String projectVideoPath=System.getProperty("user.dir")+"\\src\\main\\resources\\static\\ProjectAssets\\Video";
       Path videoPath=Paths.get(projectVideoPath,videoFile);
       File fileToDelete=videoPath.toFile();
       status=fileToDelete.delete();
	   }
       return status;
	}
	
		

}
