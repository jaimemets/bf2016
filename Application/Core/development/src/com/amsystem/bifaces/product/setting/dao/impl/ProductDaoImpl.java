package com.amsystem.bifaces.product.setting.dao.impl;

import com.amsystem.bifaces.product.setting.dao.ProductDao;
import com.amsystem.bifaces.product.setting.model.Product;
import com.amsystem.bifaces.util.AbstractDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Title: ProductDaoImpl.java <br>
 *
 * @author Jaime Aguilar (JAR)
 *         File Creation on 29/05/2017.
 */

@Repository("productDao")
public class ProductDaoImpl extends AbstractDao<String, Product> implements ProductDao {

    private static final Logger log = LogManager.getLogger(ProductDaoImpl.class.getName());

    @Transactional
    public boolean save(Product product) {
        return persist(product);
    }

    @Transactional
    public boolean updateProduct(Product product) {
        return update(product);
    }

    @Transactional
    public boolean delete(String productName) {
        Product product = getByKey(productName);
        if (product != null)
            return delete(product);

        return false;
    }

    @Transactional(readOnly = true)
    public Product loadOnlyProduct(Integer productId) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("productId", productId));
        Product product = (Product) criteria.uniqueResult();
        //TODO: Por alguna razon inicializa los planes del producto. No es lo que se espera

        if (product != null) {
            if (Hibernate.isInitialized(product.getPlanSet())) {
                log.error("[ERROR!!] No deberia estar inicializado los planes del producto: " + product.getName());

            }
        }

        return product;
    }

    @Transactional(readOnly = true)
    public Product loadProductAllPlanById(Integer productId) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("productId", productId));
        Product product = (Product) criteria.uniqueResult();
        if (product != null)
            Hibernate.initialize(product.getPlanSet());

        return product;
    }

    @Transactional(readOnly = true)
    public List<Product> loadAllProductPlan() {
        Criteria criteria = createEntityCriteria().addOrder(Order.asc("name"));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
        List<Product> productList = (List<Product>) criteria.list();

        for (Product pr : productList) {
            Hibernate.initialize(pr.getPlanSet());
        }

        return productList;
    }

    @Transactional(readOnly = true)
    public List<Product> loadAllProductByStatus(Integer status) {
        Criteria criteria = createEntityCriteria().addOrder(Order.asc("name"));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
        criteria.add(Restrictions.eq("status", status));
        return (List<Product>) criteria.list();
    }
}
