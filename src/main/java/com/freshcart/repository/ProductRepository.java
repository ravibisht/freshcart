package com.freshcart.repository;

import java.util.List;

import javax.transaction.Transactional;

import com.freshcart.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    @Modifying
    @Transactional
    @Query("delete from Product p where p.id =?1")
    void deleteById(Integer id);

    List<Product> findTop4ByOrderByIdDesc();

}
