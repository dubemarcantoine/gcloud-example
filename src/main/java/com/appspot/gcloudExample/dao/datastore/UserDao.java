/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2016 Marc-Antoine Dubé
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.appspot.gcloudExample.dao.datastore;

import com.appspot.gcloudExample.bean.User;
import com.appspot.gcloudExample.dao.interfaces.IUserDao;
import com.google.cloud.datastore.*;
import com.google.inject.Inject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marc-Antoine on 2016-05-18.
 */
public class UserDao implements IUserDao {

    //DAO properties
    private Datastore datastore;
    private KeyFactory keyFactory;

    @Inject
    public UserDao(Connection connection) {
        //TODO : Instancier la connection Datastore et la keyFactory
    }

    @Override
    public List<User> getUsers() {
        List<User> users = new ArrayList<>();
        //TODO : Faire la requete permettant de fetch tous les usagers

        //TODO : Mettre les resultats dans une liste
        //Ne pas oublier de verifier si l'attribut existe
        return  users;
    }

    @Override
    public User createUser(String name, String email) {
        //TODO : creer un usager avec un une key, username et email
        return new User(0, name, email);
    }

    @Override
    public void updateUser(Long id, String name, String email) {
        //TODO : Modifier un usager et verifier s'il existe avant
    }

    @Override
    public void deleteUser(Long id) {
        //TODO : Supprimer un usager
    }
}
