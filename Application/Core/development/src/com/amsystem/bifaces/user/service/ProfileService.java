package com.amsystem.bifaces.user.service;

import com.amsystem.bifaces.user.model.Profile;

import java.util.List;

/**
 * Title: UserProfileService.java <br>
 *
 * @author Jaime Aguilar (JAR)
 *         File Creation on 08/09/2016.
 */

public interface ProfileService {

    boolean saveProfile(Profile profile);

    boolean deleteProfileById(Integer profileId);

    Profile findProfileById(Integer profileId);

    Profile findFullProfileById(Integer profileId);

    List<Profile> findAllProfile();

    List<Profile> findProfileByIdList(List<Integer> profileIdList);

}
