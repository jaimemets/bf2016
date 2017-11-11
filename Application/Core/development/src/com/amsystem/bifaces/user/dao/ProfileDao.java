package com.amsystem.bifaces.user.dao;

import com.amsystem.bifaces.user.model.Profile;

import java.util.List;

/**
 * Title: UserProfileDao.java <br>
 *
 * @author Jaime Aguilar (JAR)
 *         File Creation on 08/09/2016.
 */
public interface ProfileDao {

    /**
     * Almacena un registro <code>Profile</code> en la base de datos
     *
     * @param profile registro para almacenar
     * @return <code>Profile</code> si el objeto <code>Profile</code> fue almacenado exitosamente;
     * <code>false</code> si ocurre una falla en el proceso de almacenamiento.
     */
    boolean saveRecord(Profile profile);

    boolean deleteById(Integer profileId);

    Profile loadById(Integer profileId);

    Profile loadFullById(Integer profileId);

    List<Profile> loadAll();

    List<Profile> loadByIdList(List<Integer> profileId);
}
