package com.amsystem.bifaces.user.service.impl;

import com.amsystem.bifaces.user.dao.UserProfileDao;
import com.amsystem.bifaces.user.model.UserProfile;
import com.amsystem.bifaces.user.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Title: UserProfileServiceImpl.java <br>
 *
 * @author Jaime Aguilar (JAR)
 *         File Creation on 08/09/2016.
 */

@Service("userProfileService")
@Transactional
public class UserProfileServiceImpl implements UserProfileService {

    @Autowired
    UserProfileDao dao;


    public UserProfile findByCod(String codProfile) {
        return dao.loadProfileByCod(codProfile);
    }

    public List<UserProfile> findAll() {
        return dao.loadAllProfile();
    }

    @Override
    public List<UserProfile> findAllProfilesByCod(List<String> codProfile) {
        return dao.loadAllProfileByCod(codProfile);
    }
}
