package com.amsystem.bifaces.product.setting.services;

import com.amsystem.bifaces.product.setting.dao.ProductDao;
import com.amsystem.bifaces.product.setting.model.Product;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Title: ProductServiceImpl.java <br>
 *
 * @author Jaime Aguilar (JAR)
 *         File Creation on 29/05/2017.
 */

@Service("productService")
@Transactional
public class ProductServiceImpl implements ProductService {

    private static final Logger log = LogManager.getLogger(ProductServiceImpl.class.getName());

    @Autowired
    private ProductDao productDao;

    @Override
    public boolean saveProduct(Product product) {
        return productDao.save(product);
    }

    @Override
    public boolean updateProduct(Product product) {
        return productDao.updateProduct(product);
    }

    @Override
    public boolean deleteProduct(String productName) {
        return productDao.delete(productName);
    }

    @Override
    public Product findById(Integer productId) {
        return productDao.getById(productId);
    }

    @Override
    public List<Product> findAllProduct() {
        return productDao.loadAllProduct();
    }

    @Override
    public List<Product> findProductByStatus(Integer status) {
        return productDao.loadProductByStatus(status);
    }
}
