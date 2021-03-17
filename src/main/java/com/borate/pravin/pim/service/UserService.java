package com.borate.pravin.pim.service;

import com.borate.pravin.pim.entities.User;
import com.borate.pravin.pim.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Pravin Borate
 * 12/03/21
 */
@Service
public class UserService {

    @Autowired
    UserRepository userRepository;


    public User getUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }
}
