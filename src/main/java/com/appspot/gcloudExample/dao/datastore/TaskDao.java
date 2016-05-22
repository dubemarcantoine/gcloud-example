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
import com.google.cloud.datastore.*;
import com.google.inject.Inject;

import java.util.ArrayList;
import java.util.List;

import static com.appspot.gcloudExample.dao.datastore.DatastoreKind.TASK;
import static com.appspot.gcloudExample.dao.datastore.DatastoreKind.USER;

/**
 * Created by Marc-Antoine on 2016-05-20.
 */
public class TaskDao implements ITaskDao {

    private Datastore datastore;
    private KeyFactory keyFactory;
    private KeyFactory userkeyFactory;

    @Inject
    public TaskDao(Connection connection) {
        this.datastore = connection.getClient();
        this.keyFactory = this.datastore.newKeyFactory().kind(TASK);
        this.userkeyFactory = this.datastore.newKeyFactory().kind(USER);
    }

    @Override
    public List<Task> getTasks(String userId) {
        List tasks = new ArrayList<>();
        Query<Entity> query = Query.entityQueryBuilder()
                .kind(TASK)
                .filter(StructuredQuery.PropertyFilter.eq("userId", this.userkeyFactory.newKey(userId)))
                .build();
        QueryResults<Entity> resultList = this.datastore.run(query);
        while (resultList.hasNext()) {
            Entity e = resultList.next();
            Task task = new Task();
            task.setId(e.key().id());
            task.setName(e.getString("name"));
            tasks.add(task);
        }
        return tasks;
    }

    @Override
    public Task createTask(Task task, String userId) {
        FullEntity<IncompleteKey> incTask = Entity.builder(this.keyFactory.newKey())
                .set("name", task.getName())
                .set("userId", this.userkeyFactory.newKey(userId))
                .build();
        Entity createdEntity = this.datastore.add(incTask);
        task.setId(createdEntity.key().id());
        return task;
    }

    @Override
    public void updateTask(Task task) {

    }

    @Override
    public void deleteTask(Long taskId) {
        System.out.println(taskId);
        Key key = this.keyFactory.newKey(taskId);
        this.datastore.delete(key);
    }
}
