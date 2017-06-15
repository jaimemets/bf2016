package com.amsystem.bifaces.product.setting.dao;

import com.amsystem.bifaces.product.setting.model.Product;

import java.util.List;

/**
 * Title: ProductDao.java <br>
 *
 * @author Jaime Aguilar (JAR)
 *         File Creation on 26/05/2017.
 */


public interface ProductDao {

    boolean save(Product product);

    boolean updateProduct(Product product);

    boolean delete(String productName);

    Product loadOnlyProduct(Integer productId);

    Product loadProductAllPlanById(Integer productId);

    List<Product> loadAllProductPlan();

    List<Product> loadAllProductByStatus(Integer status);

}
