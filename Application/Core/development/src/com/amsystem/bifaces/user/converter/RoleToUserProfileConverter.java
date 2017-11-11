package com.amsystem.bifaces.user.converter;

import com.amsystem.bifaces.user.model.Profile;
import com.amsystem.bifaces.user.service.ProfileService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Title: RoleToUserProfileConverter.java <br>
 * Clase que se encargará de asignar los identificadores individuales de perfil de usuario a las
 * entidades de perfil de usuario reales en la base de datos.
 *
 * @author Jaime Aguilar (JAR)
 *         File Creation on 08/09/2016.
 */
@Component
public class RoleToUserProfileConverter implements Converter<Object, Profile> {
    private static final Logger log = LogManager.getLogger(RoleToUserProfileConverter.class.getName());

	@Autowired
    ProfileService profileService;


    public Profile convert(Object element) {
        Integer profileId = (Integer) element;
        Profile profile = profileService.findProfileById(profileId);
        log.info("JRA Converter Profile : " + profile.getName());
        return profile;
    }
}
