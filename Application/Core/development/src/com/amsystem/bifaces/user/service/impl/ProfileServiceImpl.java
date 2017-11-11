package com.amsystem.bifaces.user.service.impl;

import com.amsystem.bifaces.user.dao.ProfileDao;
import com.amsystem.bifaces.user.model.Profile;
import com.amsystem.bifaces.user.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Title: UserProfileServiceImpl.java <br>
 *
 * @author Jaime Aguilar (JAR)
 *         File Creation on 08/09/2016.
 */

@Service("profileService")
public class ProfileServiceImpl implements ProfileService {

    @Autowired
    ProfileDao profileDao;

    @Override
    public boolean saveProfile(Profile profile) {
        return profileDao.saveRecord(profile);
    }

    @Override
    public boolean updateProfile(Profile profile) {
        return profileDao.updateRecord(profile);
    }

    @Override
    public boolean deleteProfileById(Integer profileId) {
        return profileDao.deleteById(profileId);
    }

    @Override
    public Profile findProfileById(Integer profileId) {
        return profileDao.loadById(profileId);
    }

    @Override
    public Profile findFullProfileById(Integer profileId) {
        return profileDao.loadFullById(profileId);
    }

    @Override
    public List<Profile> findAllProfile() {
        return profileDao.loadAll();
    }

    @Override
    public List<Profile> findProfileByIdList(List<Integer> profileIdList) {
        return profileDao.loadByIdList(profileIdList);
    }
}
