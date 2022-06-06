package ru.homework.librarymongo.mongock.changelog;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.client.MongoDatabase;
import ru.homework.librarymongo.domain.Author;
import ru.homework.librarymongo.repository.AuthorDao;

import java.sql.Date;

@ChangeLog
public class InitMongoDBDataChangelog {

    @ChangeSet(order = "001", id = "dropDb", author = "romanvr", runAlways = true)
    public void dropDb(MongoDatabase db) {
        db.drop();
    }

    @ChangeSet(order = "002", id = "insertAuthors", author = "romanvr")
    public void initAuthors(AuthorDao repository) {
        repository.save(new Author("ivan", "ivanov", Date.valueOf("2022-01-01"), "man"));
    }
}
