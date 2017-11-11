package com.amsystem.bifaces.user.service;

import com.amsystem.bifaces.user.model.User;

import java.util.List;

/**
 * Title: UserService.java <br>
 *
 * @author Jaime Aguilar (JAR)
 *         File Creation on 08/09/2016.
 */
public interface UserService {

    /**
     * Almacena un registro usaurio en sistema
     *
     * @param user registro para almacenar
     * @return <code>true</code> si el objeto <code>User</code> fue almacenado exitosamente;
     * <code>false</code> si ocurre una falla en el proceso de almacenamiento.
     */
    boolean saveUser(User user);

    /**
     * Actualiza los valores del usuario en el sistema
     *
     * @param user usuario para actualizar
     * @return <code>true</code> si el objeto <code>User</code> fue actualizado exitosamente;
     * <code>false</code> si ocurre una falla en el proceso de actualizacion.
     */
    boolean updateUser(User user);

    boolean deleteUserBySSO(String sso);

    User findById(int id);

    User findBySSO(String sso);

    List<User> findAllUsers();

    boolean isUserSSOUnique(Integer id, String sso);

}
