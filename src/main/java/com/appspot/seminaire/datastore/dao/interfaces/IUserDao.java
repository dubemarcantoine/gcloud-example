package com.appspot.seminaire.datastore.dao.interfaces;

import com.appspot.seminaire.datastore.bean.User;

import java.util.List;

/**
 * Created by Marc-Antoine on 2016-05-18.
 */
public interface IUserDao {

    public List<User> getUsers();
}
