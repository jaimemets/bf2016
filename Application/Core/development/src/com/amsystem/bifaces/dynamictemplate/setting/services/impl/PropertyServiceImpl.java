package com.amsystem.bifaces.dynamictemplate.setting.services.impl;

import com.amsystem.bifaces.dynamictemplate.setting.dao.PropertyDao;
import com.amsystem.bifaces.dynamictemplate.setting.dao.PropertyItemLabelDao;
import com.amsystem.bifaces.dynamictemplate.setting.dao.PropertyOptionItemDao;
import com.amsystem.bifaces.dynamictemplate.setting.dao.PropertyTemplateDao;
import com.amsystem.bifaces.dynamictemplate.setting.model.Property;
import com.amsystem.bifaces.dynamictemplate.setting.model.PropertyOptionItem;
import com.amsystem.bifaces.dynamictemplate.setting.model.PropertyOptionItemLabel;
import com.amsystem.bifaces.dynamictemplate.setting.services.PropertyService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Title: PropertyBoImpl.java
 * @author jaguilar (JAR)
 * File Creation on 09/04/2016
 */
@Service("propertyService")
@Transactional
public class PropertyServiceImpl implements PropertyService {

    @Autowired
    private PropertyDao propertyDao;

    @Autowired
    private PropertyOptionItemDao propertyOptionItemDao;

    @Autowired
    private PropertyItemLabelDao propertyItemLabelDao;

    @Autowired
    private PropertyTemplateDao propertyTemplateDao;

    private static final Logger log = LogManager.getLogger(PropertyServiceImpl.class.getName());



    public boolean addProperty(String propertyName) {
        return propertyDao.save(propertyName);
    }

    public boolean addOptionItemProperty(PropertyOptionItem optionItem){
        return propertyOptionItemDao.save(optionItem);
    }

    public boolean addItemLabelProperty(PropertyOptionItemLabel optionItemLabel){
        return propertyItemLabelDao.save(optionItemLabel);
    }


    public boolean deleteProperty(Property property) {
        boolean success = false;
        if(!isAssociate(property))
            success = propertyDao.delete(property.getName());

        return success;
    }

    public boolean deleteOptionItemProperty(PropertyOptionItem optionItem) {
        return propertyOptionItemDao.delete(optionItem);
    }

    public boolean deleteItemLabelProperty(PropertyOptionItemLabel optionItemLabel) {
        return propertyItemLabelDao.delete(optionItemLabel.getPoilId());
    }


    public boolean updateProperty(Property property) {
        return propertyDao.updateProperty(property);
    }

    public boolean updateOptionItemProperty(PropertyOptionItem propertyOptionItem) {
        return propertyOptionItemDao.updateOptionItem(propertyOptionItem);
    }

    public boolean updateItemLabelProperty(PropertyOptionItemLabel propertyOptionItemLabel) {
        return propertyItemLabelDao.update(propertyOptionItemLabel);
    }


    public Property findPropertyById(Integer idProperty, boolean optimize) {
        Property property = propertyDao.loadPropertyById(idProperty);

        if(!optimize) {
            List<PropertyOptionItem> optionItemList = findPropertyOptionItem(property.getPropertyId());
            for (PropertyOptionItem poi : optionItemList){
                List<PropertyOptionItemLabel> propertyOptionItemLabel = findPropertyOptionItemLabel(poi.getPoiId());
                poi.setItemLabels((Set<PropertyOptionItemLabel>) propertyOptionItemLabel);
            }
            property.setPropertyOptionItems(new HashSet<>(optionItemList));
        }

        return property;
    }


    @Override
    public List<PropertyOptionItem> findPropertyOptionItem(Integer propertyId) {
        return propertyOptionItemDao.loadPropertyOptionItem(propertyId);
    }


    @Override
    public List<PropertyOptionItemLabel> findPropertyOptionItemLabel(Integer poiId) {
        return propertyItemLabelDao.loadAllPropertyItemLabelById(poiId);
    }


    @Override
    public List<Property> findAllProperty() {
        List<Property> allProperty = propertyDao.loadAllProperty();

        for (Property property : allProperty) {
            List<PropertyOptionItem> optionItemList = findPropertyOptionItem(property.getPropertyId());

            for (PropertyOptionItem poi : optionItemList){
                List<PropertyOptionItemLabel> propertyOptionItemLabel = findPropertyOptionItemLabel(poi.getPoiId());
                Set<PropertyOptionItemLabel> propertyOptionItemLabelSet = new HashSet<>(propertyOptionItemLabel);
                poi.setItemLabels(propertyOptionItemLabelSet);
            }

            property.setPropertyOptionItems(new HashSet<>(optionItemList));
        }

        return allProperty;
    }


    public List<Property> findAllPropertyByIds(List idsProperty) {
        return propertyDao.loadPropertyListByIdList(idsProperty);

    }


    public boolean isAssociate(Property selectedProp) {
        return propertyTemplateDao.isPropertyAssociatedToTemplate(selectedProp.getPropertyId());
    }


    public boolean cloneProperty(String propertyCloneName, Property selectedProp) {
        boolean success = false;
        //1 Almacear la propiedad clonada
        if(propertyDao.saveManualTransaction(propertyCloneName)) {
            Property cloneProperty = propertyDao.loadPropertyByName(propertyCloneName);
            //Actualizando valores de la propiedad clonada
            cloneProperty.setDefaultValue(selectedProp.getDefaultValue());
            cloneProperty.setEditable(selectedProp.isEditable());
            cloneProperty.setRequired(selectedProp.isRequired());
            cloneProperty.setVisible(selectedProp.isVisible());
            cloneProperty.setExpressionValidator(selectedProp.getExpressionValidator());
            cloneProperty.setLabel(selectedProp.getLabel());
            cloneProperty.setFormula(selectedProp.getFormula());
            cloneProperty.setMask(selectedProp.getMask());
            cloneProperty.setRenderingType(selectedProp.getRenderingType());
            cloneProperty.setParent(selectedProp.getParent());

            if(propertyDao.updateManualTransaction(cloneProperty)) {
                //List<PropertyOptionItem> optionItemSelectedList = selectedProp.getPropertyOptionItems();
                Iterator<PropertyOptionItem> optionItemIterator = null;
                List<PropertyOptionItem> optionItemCloneList = new ArrayList<>();

                if (selectedProp.getPropertyOptionItems() != null) {
                    optionItemIterator = selectedProp.getPropertyOptionItems().iterator();

                    while (optionItemIterator.hasNext()) {
                        PropertyOptionItem optionItem = optionItemIterator.next();
                        PropertyOptionItem optionItemClone = new PropertyOptionItem(optionItem.getValue(), optionItem.getDescription(), optionItem.getProperty());
                        optionItemClone.setPoiId(optionItem.getPoiId());
                        optionItemCloneList.add(optionItemClone);

                    }
                }

                if(!optionItemCloneList.isEmpty()) {
                    if (propertyOptionItemDao.saveBatch(optionItemCloneList)) {
                        optionItemCloneList.clear();
                        int indexCloneList = -1;
                        optionItemCloneList = propertyOptionItemDao.loadPropertyOptionItem(cloneProperty.getPropertyId());

                        optionItemIterator = selectedProp.getPropertyOptionItems().iterator();
                        while (optionItemIterator.hasNext()) {
                            PropertyOptionItem optionItem = optionItemIterator.next();
                            Set<PropertyOptionItemLabel> itemLabels = optionItem.getItemLabels();
                            //Clonando Labels de cada Item
                            if (itemLabels != null) {
                                Set<PropertyOptionItemLabel> itemLabelsNew = new HashSet<>();

                                for (PropertyOptionItem poiClone : optionItemCloneList) {
                                    if (optionItem.getDescription().equalsIgnoreCase(poiClone.getDescription())) {

                                        indexCloneList = optionItemCloneList.indexOf(poiClone);
                                        for (PropertyOptionItemLabel poil : itemLabels) {
                                            PropertyOptionItemLabel optionItemLabel = new PropertyOptionItemLabel(poil.getDescription(), poil.getLocale(), poil.getPropertyOptionItem());
                                            itemLabelsNew.add(optionItemLabel);
                                        }

                                    }
                                }

                                if (indexCloneList > 0) {
                                    optionItemCloneList.remove(indexCloneList);
                                    indexCloneList = -1;
                                }

                                //Guardando lables clonados
                                if (!itemLabelsNew.isEmpty()) propertyItemLabelDao.saveBatch(itemLabelsNew);
                            }
                        }
                    }
                }
            }else{
                log.error("Error en la actualizacion de datos de la propiedad clonada.." + cloneProperty.getName());
            }
        }else {
            log.error("Error registrando la propiedad..." + propertyCloneName);
        }

        return success;
    }


    public PropertyDao getPropertyDao() {
        return propertyDao;
    }

    public void setPropertyDao(PropertyDao propertyDao) {
        this.propertyDao = propertyDao;
    }

    public PropertyOptionItemDao getPropertyOptionItemDao() {
        return propertyOptionItemDao;
    }

    public void setPropertyOptionItemDao(PropertyOptionItemDao propertyOptionItemDao) {
        this.propertyOptionItemDao = propertyOptionItemDao;
    }

    public PropertyItemLabelDao getPropertyItemLabelDao() {
        return propertyItemLabelDao;
    }

    public void setPropertyItemLabelDao(PropertyItemLabelDao propertyItemLabelDao) {
        this.propertyItemLabelDao = propertyItemLabelDao;
    }

    public PropertyTemplateDao getPropertyTemplateDao() {
        return propertyTemplateDao;
    }

    public void setPropertyTemplateDao(PropertyTemplateDao propertyTemplateDao) {
        this.propertyTemplateDao = propertyTemplateDao;
    }
}
