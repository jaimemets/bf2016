package com.amsystem.bifaces.product.setting.services;

import com.amsystem.bifaces.product.setting.model.Product;

import java.util.List;

/**
 * Title: ProductService.java <br>
 *
 * @author Jaime Aguilar (JAR)
 *         File Creation on 29/05/2017.
 */


public interface ProductService {

    boolean saveProduct(Product product);

    boolean updateProduct(Product product);

    boolean deleteProduct(String productName);

    Product findById(Integer productId);

    List<Product> findAllProduct();

    List<Product> findProductByStatus(Integer status);
}
