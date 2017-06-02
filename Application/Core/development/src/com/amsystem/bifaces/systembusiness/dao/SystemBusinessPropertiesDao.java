package com.amsystem.bifaces.systembusiness.dao;

import com.amsystem.bifaces.systembusiness.model.SystemBusinessProperties;

import java.util.List;

/**
 * Title: SystemBusinessPropertiesDao.java <br>
 *
 * @author Jaime Aguilar (JAR)
 *         File Creation on 22/05/2017.
 */


public interface SystemBusinessPropertiesDao {

    boolean save(SystemBusinessProperties systemBusinessProperties);

    boolean updateSystemBP(SystemBusinessProperties systemBusinessProperties);

    boolean delete(String codSystemBP);

    SystemBusinessProperties loadSystemBPByCod(String codSystemBP);

    List<SystemBusinessProperties> loadAllSystemBP();

    List<SystemBusinessProperties> loadSystemBPByModule(String codModule);


}
