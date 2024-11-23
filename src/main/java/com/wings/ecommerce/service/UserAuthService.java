package com.wings.ecommerce.service;

import com.wings.ecommerce.models.User;
import com.wings.ecommerce.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
public class UserAuthService  implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;

    public User loadUserByUserId(Integer id){
        Optional<User> user =userRepo.findById(id);
        if(user.isPresent()){
            return user.get();
        }
        else
            throw new UsernameNotFoundException("User ID not found");
    }

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user= userRepo.findByUsername(username);
        if(user.isPresent()){
            return user.get();
        }
        else
            throw new UsernameNotFoundException("User ID not found");
    }



}
