package com.amsystem.bifaces.user.dao;

import com.amsystem.bifaces.user.model.UserProfile;

import java.util.List;

/**
 * Title: UserProfileDao.java <br>
 *
 * @author Jaime Aguilar (JAR)
 *         File Creation on 08/09/2016.
 */
public interface UserProfileDao {

    List<UserProfile> findAll();

    List<UserProfile> loadProfileByIds(List<Integer> idList);

    UserProfile findByType(String type);

    UserProfile findById(int id);
}
