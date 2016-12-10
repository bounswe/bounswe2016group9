package boun.cmpe451.group9.Service.CustomUserDetails;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;


public interface CustomUserDetailsService extends UserDetailsService {

    UserDetails loadUserByUsername(String username);
}
