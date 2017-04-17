package io.dexter.rest;

import java.security.Principal;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller for managing UI Application request.
 */
@RestController
public class UiApplication {
	
	/**
	 * Method to handle <code>/user</code> request 
	 * 
	 * @param user logged user 
	 * @return information of logged user
	 */
  @RequestMapping("/user")
  public Principal user(Principal user) {
    return user;
  }


}