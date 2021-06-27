package com.freshcart.service;


import org.springframework.web.multipart.MultipartFile;

interface ImageAndVideoServices {


    String saveUserProfileImage(MultipartFile imageFile) throws Exception;

    String saveProductImage(MultipartFile[] imageFiles) throws Exception;

    String saveProductVideo(MultipartFile videoFile) throws Exception;

    String saveProductCategoryImage(MultipartFile imageFile) throws Exception;

    boolean deleteUserProfileImage(String imageFile);

    boolean deleteProductCategoryImage(String imageFile);

    boolean deleteProductVideo(String videoFile);

    boolean deleteProductImages(String imageFile);


}
