package com.zakharov.service;

import com.zakharov.dao.UserDao;
import com.zakharov.dmo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.util.List;

@Component
public class UserService {

    @Autowired
    UserDao userDao;

    public List<User> getUsers(){
        return userDao.getUsersList();
    }

    public User getUserById(long id){
        return userDao.getById(id);
    }

    public void deleteUser(long id){
        userDao.deleteUser(id);
    }

    public void updateUser(long id,User user){
        User userToUpdate = userDao.getById(id);
        userToUpdate.setName(user.getName());
        userToUpdate.setSurname(user.getSurname());
        userDao.updateUser(userToUpdate);
    }
}
