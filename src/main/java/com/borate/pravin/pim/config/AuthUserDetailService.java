package com.borate.pravin.pim.config;

import com.borate.pravin.pim.entities.User;
import com.borate.pravin.pim.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * @author Pravin Borate
 * 12/03/21
 */
@Service
public class AuthUserDetailService implements UserDetailsService {

    @Autowired
    UserService userService;

    @Override
    public AuthUserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        if (!email.isEmpty()) {
            User user = userService.getUserByEmail(email);
            if (user != null) {
                List<String> grantedAuthority = Collections.singletonList(user.getRole());
                return new AuthUserDetails(user, grantedAuthority);
            }
        }
        throw new UsernameNotFoundException("Not Found " + email);
    }
}
