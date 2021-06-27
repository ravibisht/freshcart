package com.freshcart.service;

import com.freshcart.model.ProductCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductCategoryServices {

    List<ProductCategory> allCategories();

    ProductCategory findById(int id);

    void saveProjectCategory(ProductCategory projectCategory);

    void saveProjectCategoryWithImage(ProductCategory projectCategory, MultipartFile image);

    void deleteProjectCategoryById(int id);

    Long countAllProjectCategories();

    Page<ProductCategory> showProjectCategoryByPage(Pageable pageable);
}
