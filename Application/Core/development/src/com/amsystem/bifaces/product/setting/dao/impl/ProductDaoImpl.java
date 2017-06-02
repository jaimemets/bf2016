package com.amsystem.bifaces.product.setting.dao.impl;

import com.amsystem.bifaces.product.setting.dao.ProductDao;
import com.amsystem.bifaces.product.setting.model.Product;
import com.amsystem.bifaces.util.AbstractDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Criteria;
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

    @Transactional(readOnly = false)
    public boolean save(Product product) {
        return persist(product);
    }

    @Transactional(readOnly = false)
    public boolean updateProduct(Product product) {
        return update(product);
    }

    @Transactional(readOnly = false)
    public boolean delete(String productName) {
        Product product = getByKey(productName);
        if (product != null)
            return delete(product);

        return false;
    }

    public Product getById(Integer productId) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("productId", productId));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
        return (Product) criteria.uniqueResult();
    }

    @Transactional(readOnly = false)
    public List<Product> loadAllProduct() {
        Criteria criteria = createEntityCriteria().addOrder(Order.asc("name"));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
        return (List<Product>) criteria.list();
    }

    @Transactional(readOnly = false)
    public List<Product> loadProductByStatus(Integer status) {
        Criteria criteria = createEntityCriteria().addOrder(Order.asc("name"));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
        criteria.add(Restrictions.eq("status", status));
        return (List<Product>) criteria.list();
    }
}
