package com.amsystem.bifaces.dynamictemplate.setting.dao.impl;

import com.amsystem.bifaces.dynamictemplate.setting.dao.PropertyItemLabelDao;
import com.amsystem.bifaces.dynamictemplate.setting.model.PropertyOptionItem;
import com.amsystem.bifaces.dynamictemplate.setting.model.PropertyOptionItemLabel;
import com.amsystem.bifaces.util.AbstractDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Title: PropertyItemLabelDaoImpl.java <br>
 *
 * @author Jaime Aguilar (JAR)
 *         File Creation on 01/05/2017.
 */

@Repository("propertyItemLabelDao")
public class PropertyItemLabelDaoImpl extends AbstractDao<Integer, Integer> implements PropertyItemLabelDao {

    private static final Logger log = LogManager.getLogger(PropertyOptionItemDaoImpl.class.getName());

    private JdbcTemplate jdbcTemplate;

    @Override
    public boolean save(PropertyOptionItemLabel propertyOptionItemLabel) {
        jdbcTemplate = new JdbcTemplate(getDataSource());
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO PROPERTYOPTIONITEMLABEL ")
                .append("(IDPOI, VALUE, LOCALE) ")
                .append(" VALUES (?,?,?)");

        log.debug("sql: " + sql);

        int rowAffected = jdbcTemplate.update(sql.toString(), propertyOptionItemLabel.getPropertyOptionItem().getPoiId(),
                propertyOptionItemLabel.getDescription(), propertyOptionItemLabel.getLocale());

        return rowAffected > 0;
    }

    @Override
    public boolean saveBatch(final Set<PropertyOptionItemLabel> itemLabelsList){
        jdbcTemplate = new JdbcTemplate(getDataSource());
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO PROPERTYOPTIONITEMLABEL ")
                .append("(IDPOI, VALUE, LOCALE) ")
                .append(" VALUES (?,?,?)");

        log.debug("sql: " + sql);

        int rowAffected = jdbcTemplate.batchUpdate(sql.toString(), new BatchPreparedStatementSetter() {

            @Override
            public void setValues(PreparedStatement ps, int i)
                    throws SQLException {

                PropertyOptionItemLabel optionItemLabel = (PropertyOptionItemLabel) itemLabelsList.toArray()[i];
                ps.setInt(1, optionItemLabel.getPropertyOptionItem().getPoiId());
                ps.setString(2, optionItemLabel.getDescription());
                ps.setString(3, optionItemLabel.getLocale());

            }

            @Override
            public int getBatchSize() {
                return itemLabelsList.size();
            }
        }).length;

        return rowAffected > 0;
    }

    @Override
    public boolean delete(Integer poilId) {
        jdbcTemplate = new JdbcTemplate(getDataSource());
        StringBuilder sql = new StringBuilder();
        sql.append("DELETE FROM PROPERTYOPTIONITEMLABEL ")
                .append("( WHERE IDPOIL = ? ")
                .append(" VALUES (?)");

        log.debug("sql: " + sql);

        int rowAffected = jdbcTemplate.update(sql.toString(), poilId);

        return rowAffected>0;
    }

    @Override
    public boolean update(PropertyOptionItemLabel propertyOptionItemLabel) {
        jdbcTemplate = new JdbcTemplate(getDataSource());
        StringBuilder sql = new StringBuilder();

        sql.append("UPDATE PROPERTYOPTIONITEMLABEL SET ")
                .append("VALUE = ? ,")
                .append("LOCALE = ? ")
                .append("( WHERE IDPOIL = ? ) ");

        log.debug("sql: " + sql);

        int rowAffected = jdbcTemplate.update(sql.toString(), propertyOptionItemLabel.getDescription(),
                propertyOptionItemLabel.getLocale(), propertyOptionItemLabel.getPoilId());

        return rowAffected>0;
    }

    @Override
    public List<PropertyOptionItemLabel> loadAllPropertyItemLabelById(Integer propertyOptionItemId) {
        List<PropertyOptionItemLabel> propertyOptionItemLabels = new ArrayList<>();
        jdbcTemplate = new JdbcTemplate(getDataSource());

        String query = "SELECT * FROM PROPERTYOPTIONITEMLABEL WHERE IDPOI = ? ";

        List<Map<String, Object>> mapList = jdbcTemplate.queryForList(query, propertyOptionItemId);

        for (Map<String, Object> map : mapList){
            PropertyOptionItem optionItem = new PropertyOptionItem();
            optionItem.setPoiId((Integer) map.get("IDPOI"));
            PropertyOptionItemLabel optionItemLabel = new PropertyOptionItemLabel((String) map.get("VALUE"), (String) map.get("LOCALE"), optionItem);
            optionItemLabel.setPoilId((Integer) map.get("IDPOIL"));
            propertyOptionItemLabels.add(optionItemLabel);
        }

        return propertyOptionItemLabels;
    }

    @Override
    public PropertyOptionItemLabel loadPropertyItemLabelById(Integer pilId) {
        return null;
    }
}
