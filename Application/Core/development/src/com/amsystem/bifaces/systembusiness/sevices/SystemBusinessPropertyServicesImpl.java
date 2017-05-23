package com.amsystem.bifaces.systembusiness.sevices;

import com.amsystem.bifaces.systembusiness.dao.SystemBusinessPropertiesDao;
import com.amsystem.bifaces.systembusiness.model.SystemBusinessProperties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Title: SystemBusinessPropertyServicesImpl.java <br>
 *
 * @author Jaime Aguilar (JAR)
 *         File Creation on 22/05/2017.
 */

@Service("systemBPService")
@Transactional
public class SystemBusinessPropertyServicesImpl implements SystemBusinessPropertyServices{

    private static final Logger log = LogManager.getLogger(SystemBusinessPropertyServicesImpl.class.getName());

    @Autowired
    private SystemBusinessPropertiesDao systemBPDao;

    @Override
    public boolean saveSystemBP(SystemBusinessProperties systemBusinessProperties) {
        return false;
    }

    @Override
    public boolean updateSystemBP(SystemBusinessProperties systemBusinessProperties) {
        return systemBPDao.updateSystemBP(systemBusinessProperties);
    }

    @Override
    public boolean deleteSystemBP(String codSystemBP) {
        return false;
    }

    @Override
    public SystemBusinessProperties findByCod(String codSystemBP) {
        return null;
    }

    @Override
    public List<SystemBusinessProperties> findAllSystemBP() {
        return systemBPDao.loadAllSystemBP();
    }

    @Override
    public List<SystemBusinessProperties> findSystemBPByModule(String codModule) {
        return null;
    }
}
