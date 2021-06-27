package com.freshcart.controller;

import com.freshcart.exception.ProductNotFoundException;
import com.freshcart.model.Product;
import com.freshcart.model.ProductCategory;
import com.freshcart.model.User;
import com.freshcart.service.ProductCategoryServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;


@Controller
public class ProductController {

    @Autowired
    private Product ps;


    @Autowired
    private ProductCategoryServices pcs;


    @RequestMapping(value = "/uploadProduct")
    public String addProducts(Model model) {

        model.addAttribute("pc", pcs.allCategories());

        model.addAttribute("navName", "Back to Home");
        model.addAttribute("navUrl", "/user/feed");
        model.addAttribute("title", "Product Upload Form");

        return "user/ProductUploadForm";

    }

    @PostMapping(value = "/user/upload/product")
    public String addProduct(Product product, HttpSession session, @RequestParam int categoryId,
                             @RequestParam("images") MultipartFile images[], @RequestParam("videofile") MultipartFile video, Model model) {

        int loggedInUserId = (int) session.getAttribute("loggedInUserId");

        product.setCategoryId(categoryId);
        product.setCreatedDate(new Date());
        product.setUpdatedDate(new Date());
        product.setUserId(loggedInUserId);

        ps.saveProductWithMedia(product, images, video);

        model.addAttribute("navName", "Back to Home");
        model.addAttribute("navUrl", "/user/feed");
        model.addAttribute("status", "true");
        return "user/productUploadForm";
    }

    @RequestMapping(value = "Product/edit/{ProductId}")
    public String editProductForm(@PathVariable int ProductId, HttpSession session, Model model) throws ProductNotFoundException {

        String username = ((User) session.getAttribute("loggedInUser")).getUsername();

        try {

            Product product = ps.getProduct(ProductId);

            int loggedInUserId = (int) session.getAttribute("loggedInUserId");
            int productUserId = product.getUserId();

            if (loggedInUserId == productUserId) {
                model.addAttribute("product", product);
                model.addAttribute("navName", "Back to Profile");
                model.addAttribute("navUrl", "/profile/" + username);

                return "user/ProductEditForm";
            }

        } catch (EntityNotFoundException message) {

            throw new ProductNotFoundException(productId);
        }


        return "error/error";

    }

    @PostMapping(value = "/Product/edit")
    public String editProduct(Product Product, HttpSession session, Model model) {

        String username = ((User) session.getAttribute("loggedInUser")).getUsername();
        Product newProduct = ps.getProduct(Product.getId());

        newProduct.setProductName(Product.getProductName());
        newProduct.setExternalLink(Product.getExternalLink());
        newProduct.setDescription(Product.getDescription());
        newProduct.setShortDesc(Product.getShortDesc());
        newProduct.setUpdatedDate(new Date());

        ps.saveProduct(newProduct);

        model.addAttribute("navName", "Back to Profile");
        model.addAttribute("navUrl", "/profile/" + username);
        model.addAttribute("Product", newProduct);
        model.addAttribute("status", "true");

        return "user/productEditForm";

    }


    @RequestMapping(value = {"share/product/{productId}", "show/product/{productId}"})
    public String showProduct(@PathVariable("productId") int productId, Model model) throws ProductNotFoundException {

        try {
            Product product = ps.getProduct(product);
            int categoryId = Product.getCategoryId();
            model.addAttribute("product", product);
            model.addAttribute("categoryId", categoryId);
            model.addAttribute("title", "Show Product");
            return "user/showProduct";
        } catch (EntityNotFoundException exception) {

            throw new ProductNotFoundException(ProductId);
        }


    }


    @RequestMapping(value = "/Product/delete/{ProductId}")
    public String deleteProduct(@PathVariable int ProductId, HttpSession session, Model mv) throws ProductNotFoundException {

        try {

            int ProductUserId = ps.getProduct(ProductId).getUserId();

            int loggedInUserId = (int) session.getAttribute("loggedInUserId");
            String username = ((User) session.getAttribute("loggedInUser")).getUsername();

            if (ProductUserId == loggedInUserId) {
                ps.deleteProductById(ProductId);

                return "redirect:/profile/" + username;
            }

        } catch (EntityNotFoundException message) {
            throw new ProductNotFoundException(ProductId);
        }
        return "error/error";
    }


    @RequestMapping(value = {"/", "index", "index.html"})
    public String showProductForWelcomePage(Model model,
                                            HttpSession session) {

        List<Product> Products = ps.getLatest4Product();
        List<ProductCategory> categories = pcs.allCategories();
        model.addAttribute("Products", Products);
        session.setAttribute("categories", categories);

        model.addAttribute("title", "Home");
        model.addAttribute("active_home", "active-item");
        return "user/Index";
    }

    @RequestMapping("/user/feed")
    public String homeProducts(Model model, HttpSession session) {

        List<Product> Products = ps.getAllProduct();
        List<ProductCategory> categories = pcs.allCategories();
        model.addAttribute("Products", Products);
        model.addAttribute("categories", categories);

        String username;
        try {
            username = ((User) session.getAttribute("loggedInUser")).getUsername();
        } catch (NullPointerException ne) {
            return "redirect:/login";
        }

        if (username == null)
            return "redirect:/login";

        model.addAttribute("active_feed", "active-item");
        model.addAttribute("title", "Feed");
        return "user/feeds";
    }

    @RequestMapping("/Product/category/show/{id}")
    public String getCategory(@PathVariable int id, Model model) {

        model.addAttribute("category", pcs.findById(id));
        model.addAttribute("title", " Product Category");
        model.addAttribute("active_category", "active-item");
        return "user/category";
    }
}
