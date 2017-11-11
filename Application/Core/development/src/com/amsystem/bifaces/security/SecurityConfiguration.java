package com.amsystem.bifaces.security;

import com.amsystem.bifaces.user.model.Profile;
import com.amsystem.bifaces.user.service.ProfileService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import java.util.List;


/**
 * Title: SecurityConfiguration.java <br>
 * Clase para el control de seguridad de los usuarios
 *
 * @author Jaime Aguilar (JAR)
 *         File Creation on 12/09/2016.
 */

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    @Qualifier("userDetailsService")
    UserDetailsService userDetailsService;

    @Autowired
    PersistentTokenRepository tokenRepository;

    @Autowired
    ProfileService profileService;

    private static final Logger log = LogManager.getLogger(SecurityConfiguration.class.getName());

    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
        auth.authenticationProvider(authenticationProvider());
    }


    /**
     * Configuracion de permisos y funciones en el sistema
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/", "/home")
                        //.access("hasRole('USER') or hasRole('ADMIN') or hasRole('DBA')")
                .access(getAccessAllProfiles())
                .antMatchers("/newuser/**", "/delete-user-*")
                .access("hasRole('ADMIN')")
                .antMatchers("/edit-user-*")
                .access("hasRole('ADMIN') or hasRole('DBA')").and()
                .formLogin().loginPage("/login")
                .loginProcessingUrl("/login")
                .usernameParameter("username")
                .passwordParameter("password").and()
                .rememberMe().rememberMeParameter("remember-me").tokenRepository(tokenRepository)
                .tokenValiditySeconds(86400).and()
                .csrf().and().exceptionHandling().accessDeniedPage("/Access_Denied");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Bean
    public PersistentTokenBasedRememberMeServices getPersistentTokenBasedRememberMeServices() {
        PersistentTokenBasedRememberMeServices tokenBasedService = new PersistentTokenBasedRememberMeServices(
                "remember-me", userDetailsService, tokenRepository);
        return tokenBasedService;
    }

    @Bean
    public AuthenticationTrustResolver getAuthenticationTrustResolver() {
        return new AuthenticationTrustResolverImpl();
    }

    /**
     * Busca todos los perfiles registrados en el sistem.
     *
     * @return Cadena con todos los roles del sistema
     */
    private String getAccessAllProfiles() {
        String strProfiles = "";
        List<Profile> allProfile = profileService.findAllProfile();

        for (Profile profile : allProfile) {
            strProfiles += "hasRole('".concat(profile.getName()).concat("')");
            if (allProfile.indexOf(profile) != allProfile.size() - 1) {
                strProfiles += " or ";
            }
        }
        log.debug("JRA allProfiles: " + strProfiles);
        return strProfiles;
    }

}

