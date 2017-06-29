package com.amsystem.bifaces.dynamictemplate.setting.dao.impl;

import com.amsystem.bifaces.dynamictemplate.setting.dao.TemplateHibDao;
import com.amsystem.bifaces.dynamictemplate.setting.model.Template;
import com.amsystem.bifaces.util.AbstractDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Title: TemplateHibDaoImpl.java <br>
 *
 * @author Jaime Aguilar (JAR)
 *         File Creation on 15/06/2017.
 */

@Repository("templateHibDao")
public class TemplateHibDaoImpl extends AbstractDao<Integer, Template> implements TemplateHibDao {

    private static final Logger log = LogManager.getLogger(TemplateHibDaoImpl.class.getName());

    @Override
    @Transactional(readOnly = true)
    public Template loadTemplateById(Integer idTr) {
        Template template = getByKey(idTr);
        if (template != null) {
            Hibernate.initialize(template.getPropertySet());
            Hibernate.initialize(template.getProductTemplateLevelSet());
        }

        return template;
    }

    @Override
    @Transactional(readOnly = true)
    public Template loadTemplateByName(String templateName) {
        log.info("Templante Name : " + templateName);
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("name", templateName));
        Template template = (Template) criteria.uniqueResult();
        if (template != null) {
            Hibernate.initialize(template.getPropertySet());
            Hibernate.initialize(template.getProductTemplateLevelSet());
        }
        return template;
    }


}
