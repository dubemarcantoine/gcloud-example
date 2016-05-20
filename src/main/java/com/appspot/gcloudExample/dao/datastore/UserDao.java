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

import static com.appspot.gcloudExample.dao.datastore.DatastoreKind.*;

/**
 * Created by Marc-Antoine on 2016-05-18.
 */
public class UserDao implements IUserDao {

    //DAO properties
    private Datastore datastore;
    private KeyFactory keyFactory;

    @Inject
    public UserDao(Connection connection) {
        this.datastore = connection.getClient();
        this.keyFactory = datastore.newKeyFactory().kind(USER);
    }

    @Override
    public List<User> getUsers() {
        List<User> users = new ArrayList<>();
        Query<Entity> query = Query.entityQueryBuilder()
                .kind(DatastoreKind.USER)
                .build();
        //Other way of doing the query with GQL:
        //Query<Entity> query = Query.gqlQueryBuilder(Query.ResultType.ENTITY, "SELECT * FROM " + USER).build();
        QueryResults<Entity> results = datastore.run(query);
        while (results.hasNext()) {
            Entity e = results.next();
            User u = new User();
            u.setEmail(e.getString("email"));
            u.setUsername(e.getString("username"));
            u.setId(e.key().id());
            users.add(u);
        }
        return  users;
    }

    @Override
    public User createUser(String name, String email) {
        System.out.println("create user");
        User user = new User();
        FullEntity<IncompleteKey> incUser = Entity.builder(this.keyFactory.newKey())
                .set("username", name)
                .set("email", email)
                .build();
        Entity createdEntity = datastore.add(incUser);
        user.setId(createdEntity.key().id());
        user.setUsername(name);
        user.setEmail(email);
        return user;
    }

    @Override
    public void updateUser(Long id, String name, String email) {
        try {
            Key key = this.keyFactory.newKey(id);
            Entity entity = this.datastore.get(key);
            if (entity == null) {
                throw new IllegalArgumentException("No user with id '" + id + "' found");
            } else {
                entity = Entity.builder(entity)
                        .set("username", name)
                        .set("email", email)
                        .build();
                datastore.update(entity);
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteUser(Long id) {
        Key key = keyFactory.newKey(id);
        datastore.delete(key);
    }
}
