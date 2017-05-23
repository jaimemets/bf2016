package com.amsystem.bifaces.systembusiness.sevices;

import com.amsystem.bifaces.systembusiness.model.SystemBusinessProperties;

import java.util.List;

/**
 * Title: SystemBusinessPropertyServices.java <br>
 *
 * @author Jaime Aguilar (JAR)
 *         File Creation on 22/05/2017.
 */


public interface SystemBusinessPropertyServices {

    boolean saveSystemBP(SystemBusinessProperties systemBusinessProperties);

    boolean updateSystemBP(SystemBusinessProperties systemBusinessProperties);

    boolean deleteSystemBP(String codSystemBP);

    SystemBusinessProperties findByCod(String codSystemBP);

    List<SystemBusinessProperties> findAllSystemBP();

    List<SystemBusinessProperties> findSystemBPByModule(String codModule);
}
