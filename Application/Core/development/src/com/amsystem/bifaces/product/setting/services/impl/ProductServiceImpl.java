package com.amsystem.bifaces.product.setting.services.impl;

import com.amsystem.bifaces.product.setting.dao.PlanDao;
import com.amsystem.bifaces.product.setting.dao.ProductDao;
import com.amsystem.bifaces.product.setting.model.Plan;
import com.amsystem.bifaces.product.setting.model.Product;
import com.amsystem.bifaces.product.setting.services.ProductService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

/**
 * Title: ProductServiceImpl.java <br>
 *
 * @author Jaime Aguilar (JAR)
 *         File Creation on 29/05/2017.
 */

@Service("productService")
public class ProductServiceImpl implements ProductService {

    private static final Logger log = LogManager.getLogger(ProductServiceImpl.class.getName());

    @Autowired
    private ProductDao productDao;

    @Autowired
    private PlanDao planDao;

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
    public Product findProductAllPlanById(Integer productId) {
        return productDao.loadProductAllPlanById(productId);
    }

    @Override
    public Product findProductPlanById(Integer idPlan, Integer idProduct) {
        //Cargando Producto
        Product product = productDao.loadOnlyProduct(idProduct);
        if (product != null) {
            Plan plan = planDao.loadPlanProductConfigById(idPlan);
            HashSet<Plan> planSet = new HashSet<>();
            planSet.add(plan);
            product.setPlanSet(planSet);
        }

        return product;
    }

    @Override
    public List<Product> findAllProductPlan() {
        return productDao.loadAllProductPlan();
    }

    @Override
    public List<Product> findProductByStatus(Integer status) {
        return productDao.loadAllProductByStatus(status);
    }
}
