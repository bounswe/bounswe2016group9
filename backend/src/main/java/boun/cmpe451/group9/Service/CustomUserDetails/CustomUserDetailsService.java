package boun.cmpe451.group9.Service.CustomUserDetails;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * Includes methods that deals with the current user
 */
public interface CustomUserDetailsService extends UserDetailsService {

    /**
     * Retrieves userDetails by username text
     * @param username username of the current user
     * @return userDetails object
     */
    UserDetails loadUserByUsername(String username);
}
