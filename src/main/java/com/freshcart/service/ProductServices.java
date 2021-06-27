package com.freshcart.service;

import com.freshcart.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

interface ProductServices {

    void saveProject(Product project);

    void saveProjectWithMedia(Product project, MultipartFile[] images, MultipartFile video);

    List<Product> getAllProject();

    Product getProject(int project_id);

    void deleteProjectById(int project_id);

    Page<Product> showProjectByPage(Pageable pageable);

    List<Product> getLatest4Project();

    Long countAllProject();

}
