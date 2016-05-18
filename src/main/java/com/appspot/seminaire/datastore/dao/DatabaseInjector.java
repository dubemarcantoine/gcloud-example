package com.appspot.seminaire.datastore.dao;

import com.appspot.seminaire.datastore.dao.datastore.UserDao;
import com.appspot.seminaire.datastore.dao.interfaces.IUserDao;
import com.google.inject.AbstractModule;

/**
 * Created by Marc-Antoine on 2016-05-18.
 */
public class DatabaseInjector extends AbstractModule {

    @Override
    protected void configure() {
        //Bind the dao interface to the UserDao implementation in datastore package
        bind(IUserDao.class).to(UserDao.class);
    }
}
