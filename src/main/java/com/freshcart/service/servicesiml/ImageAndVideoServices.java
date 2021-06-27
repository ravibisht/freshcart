package com.freshcart.service.servicesiml;


import org.springframework.web.multipart.MultipartFile;

public interface ImageAndVideoServices {


    String saveUserProfileImage(MultipartFile imageFile) throws Exception;

    String saveProjectImage(MultipartFile[] imageFiles) throws Exception;

    String saveProjectVideo(MultipartFile videoFile) throws Exception;

    String saveProjectCategoryImage(MultipartFile imageFile) throws Exception;

    boolean deleteUserProfileImage(String imageFile);

    boolean deleteProjectCategoryImage(String imageFile);

    boolean deleteProjectVideo(String videoFile);

    boolean deleteProjectImages(String imageFile);


}
