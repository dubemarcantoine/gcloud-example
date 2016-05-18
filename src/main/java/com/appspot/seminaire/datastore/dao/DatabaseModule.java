package com.appspot.seminaire.datastore.dao;

import com.appspot.seminaire.datastore.bean.User;
import com.appspot.seminaire.datastore.dao.interfaces.IUserDao;
import com.google.inject.Inject;

import java.util.List;

/**
 * Created by Marc-Antoine on 2016-05-18.
 */
//Implement all the interfaces that the class injects
//In the implementation, call the interface and the method
public class DatabaseModule implements IUserDao {

    private IUserDao userDao;

    @Inject
    public DatabaseModule(IUserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public List<User> getUsers() {
        return userDao.getUsers();
    }
}
