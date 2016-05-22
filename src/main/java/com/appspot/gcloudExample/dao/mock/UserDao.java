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

package com.appspot.gcloudExample.dao.mock;

import com.appspot.gcloudExample.bean.User;
import com.appspot.gcloudExample.dao.interfaces.IUserDao;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marc-Antoine on 2016-05-18.
 */
public class UserDao implements IUserDao {

    @Override
    public List<User> getUsers() {
        return UserTable.getInstance().get();
    }

    @Override
    public User createUser(String name, String email) {
        User user = new User();
        user.setEmail(email);
        user.setUsername(name);
        UserTable.getInstance().add(user);
        return user;
    }

    @Override
    public void updateUser(Long id, String name, String email) {
        User user = null;
        for(User u : UserTable.getInstance().get()) {
            if(u.getId() == id) {
                user = u;
                break;
            }
        }
        if(user == null) {
            throw new IllegalArgumentException("No user with id '" + id + "' found");
        }
        else {
            user.setUsername(name);
            user.setEmail(email);
        }
     }

    @Override
    public void deleteUser(Long id) {
        List<User> toRemove = new ArrayList<>();
        for(User u : UserTable.getInstance().get()) {
            if(u.getId() == id) {
                System.out.print(id);
                toRemove.add(u);
            }
        }
        UserTable.getInstance().get().removeAll(toRemove);
    }
}
