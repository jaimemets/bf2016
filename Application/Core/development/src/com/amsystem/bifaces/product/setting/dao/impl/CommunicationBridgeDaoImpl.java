package com.amsystem.bifaces.product.setting.dao.impl;

import com.amsystem.bifaces.product.setting.dao.CommunicationBridgeDao;
import com.amsystem.bifaces.product.setting.model.CommunicationBridge;
import com.amsystem.bifaces.util.AbstractDao;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Title: CommunicationServiceDaoImpl.java <br>
 *
 * @author Jaime Aguilar (JAR)
 *         File Creation on 21/06/2017.
 */

@Repository("communicationServiceDao")
public class CommunicationBridgeDaoImpl extends AbstractDao<Integer, CommunicationBridge> implements CommunicationBridgeDao {

    @Override
    @Transactional
    public boolean save(CommunicationBridge communicationBridge) {
        return persist(communicationBridge);
    }

    @Override
    @Transactional
    public boolean updateCommunicationBridge(CommunicationBridge communicationBridge) {
        return update(communicationBridge);
    }

    @Override
    @Transactional
    public boolean deleteCommunicationBridge(Integer csId) {
        CommunicationBridge cs = getByKey(csId);
        if (cs != null)
            return delete(cs);

        return false;
    }

    @Override
    @Transactional(readOnly = true)
    public CommunicationBridge loadOnlyCommunication(Integer csId) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("cbId", csId));
        CommunicationBridge communicationBridge = (CommunicationBridge) criteria.uniqueResult();
        if (communicationBridge != null) {
            //  Hibernate.initialize(communicationBridge.getProductTemplateLevelSet());
        }
        return communicationBridge;
    }

    @Override
    @Transactional(readOnly = true)
    public CommunicationBridge loadCommunicationAndParameter(Integer csId) {
        CommunicationBridge communicationBridge = loadOnlyCommunication(csId);
        if (communicationBridge != null) {
            //   Hibernate.initialize(communicationBridge.getProductTemplateLevelSet());
            //Inicializar propiedades asociadas
        }
        return communicationBridge;
    }

    @Override
    @Transactional(readOnly = true)
    public List<CommunicationBridge> loadAllCommunicationBridge() {
        Criteria criteria = createEntityCriteria().addOrder(Order.asc("description"));
        return (List<CommunicationBridge>) criteria.list();

    }

    @Override
    @Transactional(readOnly = true)
    public List<CommunicationBridge> loadAllCommunicationAndParameterByType(Integer type) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("type", type));
        return (List<CommunicationBridge>) criteria.list();

    }

    @Override
    @Transactional(readOnly = true)
    public List<CommunicationBridge> loadAllCommunicationAndParameterByCategory(Integer category) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("category", category));
        return (List<CommunicationBridge>) criteria.list();
    }
}
