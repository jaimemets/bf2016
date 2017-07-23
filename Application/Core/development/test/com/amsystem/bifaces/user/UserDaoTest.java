package com.amsystem.bifaces.user;

import com.amsystem.bifaces.configuration.AppRootConfig;
import com.amsystem.bifaces.configuration.HibernateConfiguration;
import com.amsystem.bifaces.configuration.PrincipalConf;
import com.amsystem.bifaces.user.model.User;
import com.amsystem.bifaces.user.model.UserProfile;
import com.amsystem.bifaces.user.service.UserProfileService;
import com.amsystem.bifaces.user.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Date;

/**
 * Title: UserDaoTest.java <br>
 *
 * @author Jaime Aguilar (JAR)
 *         File Creation on 30/06/2017.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {AppRootConfig.class, HibernateConfiguration.class, PrincipalConf.class})
public class UserDaoTest {

    @Autowired
    UserService userService;

    @Autowired
    UserProfileService userProfileService;


    @Test

    //@Rollback(true)
    public void testAddUser() {
        User user = new User("testUnit", "Test", "Unit", "testunit@example.com", new Date());
        user.setPassword("123456");
        user.setUserProfiles(null);
        user.setMenuItems(null);

        userService.saveUser(user);

        User userBD = userService.findBySSO(user.getUserName());

        Assert.assertNotNull(userBD);


    }

    @Test
    public void testGetUserById() {
        User user = userService.findById(17);
        UserProfile profile = userProfileService.findByCod("USER");

        //user.getUserProfiles().add(profile);

        user.getUserProfiles().remove(profile);

        boolean flag = userService.updateUser(user);
        Assert.assertTrue("No se pudo agregar el perfil al usuario", flag);
        Assert.assertNotNull(user);

    }


}
