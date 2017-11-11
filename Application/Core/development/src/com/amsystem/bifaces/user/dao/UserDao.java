package com.amsystem.bifaces.user.dao;

import com.amsystem.bifaces.user.model.User;

import java.util.List;

/**
 * Title: UserDao.java <br>
 *
 * @author Jaime Aguilar (JAR)
 *         File Creation on 08/09/2016.
 */
public interface UserDao {

    /**
     * Almacena un registro usaurio en la base de datos
     *
     * @param user registro para almacenar
     * @return <code>true</code> si el objeto <code>User</code> fue almacenado exitosamente;
     * <code>false</code> si ocurre una falla en el proceso de almacenamiento.
     */
    boolean save(User user);

    /**
     * @param sso
     * @return
     */
    boolean deleteBySSO(String sso);

    /**
     *
     * @param id
     * @return
     */
    User loadUserById(int id);


    /**
     *
     * @param sso
     * @return
     */
    User loadUserBySSO(String sso);

    /**
     *
     * @return
     */
    List<User> loadAllUser();
}
