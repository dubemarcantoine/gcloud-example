package com.appspot.seminaire.datastore.dao.datastore;

import com.appspot.seminaire.datastore.bean.User;
import com.appspot.seminaire.datastore.dao.interfaces.IUserDao;
import com.google.cloud.datastore.*;

import java.util.ArrayList;
import java.util.List;

import static com.appspot.seminaire.datastore.dao.datastore.DATASTORE_KIND.*;

/**
 * Created by Marc-Antoine on 2016-05-18.
 */
public class UserDao implements IUserDao {

    //DAO properties
    private Datastore datastore;
    private KeyFactory keyFactory;

    public UserDao() {
        this.datastore = Connection.getConn();
        this.keyFactory = datastore.newKeyFactory().kind(USER);
    }

    @Override
    public List<User> getUsers() {
        List<User> users = new ArrayList<>();
        Query<Entity> query = Query.entityQueryBuilder()
                .kind(USER)
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
}
