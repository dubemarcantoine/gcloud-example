package com.appspot.seminaire.datastore.dao.mock;

import com.appspot.seminaire.datastore.bean.User;
import com.appspot.seminaire.datastore.dao.interfaces.IUserDao;

import java.util.List;

/**
 * Created by Marc-Antoine on 2016-05-18.
 */
public class UserDao implements IUserDao {
    @Override
    public List<User> getUsers() {
        return null;
    }
}
