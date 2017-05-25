package com.amsystem.bifaces.controller;

/**
 * Title: AppController.java <br>
 *
 * @author Jaime Aguilar (JAR)
 *         File Creation on 12/09/2016.
 */

import com.amsystem.bifaces.user.model.User;
import com.amsystem.bifaces.user.service.UserService;
import com.amsystem.bifaces.util.OperationType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.validation.Valid;
import java.util.ResourceBundle;


@Controller
@RequestMapping("/")
@SessionAttributes("roles")
public class UserCtrl extends GeneralCtrl{

    private static final Logger log = LogManager.getLogger(UserCtrl.class.getName());

    @Autowired
    UserService userService;

    @Autowired
    private ResourceBundle rb;

    /**
     * This method will list all existing users.
     */
    @RequestMapping(method = RequestMethod.POST)
    public String forward(ModelMap model) {
        model.addAttribute("loggedinuser", getPrincipal());
        return "";
    }

    /**
     * This method will provide the medium to add a new user.
     */
    @RequestMapping(value = { "/newUser" }, method = RequestMethod.GET)
    public String newUser(ModelMap model) {
        User user = new User();
        model.addAttribute("requestUsr", user);
        model.addAttribute("operation", rb.getString("newRegister_GRL"));
        model.addAttribute("operationType", OperationType.CREATE);
        model.addAttribute("loggedinuser", getPrincipal());
        return "usertool/userGeneralConfig";
    }

    /**
     * This method will be called on form submission, handling POST request for
     * saving user in database. It also validates the user input
     */
    @RequestMapping(value = { "/newuser" }, method = RequestMethod.POST)
    public String saveUser(@Valid User user, BindingResult result,
                           ModelMap model) {

        if (result.hasErrors()) {
            return "registration";
        }

		/*
		 * Preferred way to achieve uniqueness of field [sso] should be implementing custom @Unique annotation
		 * and applying it on field [sso] of Model class [User].
		 *
		 * Below mentioned peace of code [if block] is to demonstrate that you can fill custom errors outside the validation
		 * framework as well while still using internationalized messages.
		 *
		 */
        if (!userService.isUserSSOUnique(user.getUserId(), user.getUserName())) {
            //FieldError ssoError =new FieldError("user","ssoId",rb.getString("non.unique.ssoId", new String[]{user.getUserName()}, Locale.getDefault()));
            //result.addError(ssoError);
            return "registration";
        }

        userService.saveUser(user);

        model.addAttribute("success", "User " + user.getFirstName() + " "+ user.getLastName() + " registered successfully");
        model.addAttribute("loggedinuser", getPrincipal());
        //return "success";
        return "registrationsuccess";
    }


    /**
     * This method will provide the medium to update an existing user.
     */
    @RequestMapping(value = { "/editUser-{userName}"}, method = RequestMethod.GET)
    public String editUser(@PathVariable String userName, ModelMap model) {
        User user = userService.findBySSO(userName);
        model.addAttribute("requestUsr", user);
        model.addAttribute("operation", rb.getString("registryUpdate_GRL"));
        model.addAttribute("operationType", OperationType.EDIT);

        model.addAttribute("loggedinuser", getPrincipal());
        return "usertool/userGeneralConfig";
    }

    /**
     * This method will be called on form submission, handling POST request for
     * updating user in database. It also validates the user input
     */
    @RequestMapping(value = { "/edit-user-{ssoId}" }, method = RequestMethod.POST)
    public String updateUser(@Valid User user, BindingResult result,
                             ModelMap model, @PathVariable String ssoId) {

        if (result.hasErrors()) {
            return "registration";
        }

		/*//Uncomment below 'if block' if you WANT TO ALLOW UPDATING SSO_ID in UI which is a unique key to a User.
		if(!userService.isUserSSOUnique(user.getId(), user.getUserName())){
			FieldError ssoError =new FieldError("user","ssoId",messageSource.getMessage("non.unique.ssoId", new String[]{user.getUserName()}, Locale.getDefault()));
		    result.addError(ssoError);
			return "registration";
		}*/


        userService.updateUser(user);

        model.addAttribute("success", "User " + user.getFirstName() + " "+ user.getLastName() + " updated successfully");
        model.addAttribute("loggedinuser", getPrincipal());
        return "registrationsuccess";
    }


    /**
     * This method will delete an user by it's SSOID value.
     */
    @RequestMapping(value = { "/delete-user-{ssoId}" }, method = RequestMethod.GET)
    public String deleteUser(@PathVariable String ssoId) {
        userService.deleteUserBySSO(ssoId);
        return "redirect:/list";
    }



}
