package com.spring01.reviews.changelogs;

import com.github.mongobee.changeset.ChangeLog;
import com.github.mongobee.changeset.ChangeSet;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;

@ChangeLog
public class DatabaseChangelog {

    @ChangeSet(order = "001", id = "someChangeId", author = "babsAuthor")
    public void importantWorkToDo(DB db){
        // task implementation
        db.createCollection("reviews", new BasicDBObject());
    }


}