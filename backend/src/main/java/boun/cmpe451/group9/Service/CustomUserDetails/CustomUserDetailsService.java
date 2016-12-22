package boun.cmpe451.group9.Service.CustomUserDetails;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;


public interface CustomUserDetailsService extends UserDetailsService {

    /**
     * Retrieves userDetails by username text
     * @param username
     * @return userDetails object
     */
    UserDetails loadUserByUsername(String username);
}
