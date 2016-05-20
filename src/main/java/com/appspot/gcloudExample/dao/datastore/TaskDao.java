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

import com.appspot.gcloudExample.bean.Task;
import com.appspot.gcloudExample.dao.interfaces.ITaskDao;
import com.google.cloud.datastore.Datastore;
import com.google.cloud.datastore.KeyFactory;
import com.google.inject.Inject;

import java.util.List;

import static com.appspot.gcloudExample.dao.datastore.DatastoreKind.*;

/**
 * Created by Marc-Antoine on 2016-05-20.
 */
public class TaskDao implements ITaskDao {

    private Datastore datastore;
    private KeyFactory keyFactory;

    @Inject
    public TaskDao(Connection connection) {
        this.datastore = connection.getClient();
        this.keyFactory = this.datastore.newKeyFactory().kind(TASK);
    }

    @Override
    public List<Task> getTasks(Long userId) {
        return null;
    }

    @Override
    public Task createTask(Task task, Long userId) {
        return null;
    }

    @Override
    public void updateTask(Task task) {

    }

    @Override
    public void deleteTask(Long taskId) {

    }
}
