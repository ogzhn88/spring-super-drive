package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mappers.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;

@Service
public class UserService {
     @Autowired
     private UserMapper userMapper;
     @Autowired
     private HashService hashService;

     public User getUserByUserName(String username){
         return userMapper.getUser(username);
     }

     public List<User> getAllUsers(){

         return  userMapper.getUsers();
     }

    public int createUser(User user) {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        String encodedSalt = Base64.getEncoder().encodeToString(salt);
        String hashedPassword = hashService.getHashedValue(user.getPassword(), encodedSalt);
        User user2 = new User(user.getUsername(), encodedSalt,user.getFirstname(),user.getLastname(),hashedPassword);
        return userMapper.insert(user2);
    }
}
