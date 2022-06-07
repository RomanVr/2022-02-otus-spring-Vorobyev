package ru.homework.librarymongo.mongock.changelog;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.client.MongoDatabase;
import ru.homework.librarymongo.domain.Author;
import ru.homework.librarymongo.domain.Book;
import ru.homework.librarymongo.domain.BookCommentary;
import ru.homework.librarymongo.domain.Genre;
import ru.homework.librarymongo.repository.AuthorDao;
import ru.homework.librarymongo.repository.BookCommentaryDao;
import ru.homework.librarymongo.repository.BookDao;
import ru.homework.librarymongo.repository.GenreDao;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@ChangeLog
public class InitMongoDBDataChangelog {

    private Author author;
    private Genre genre;
    private Book book1;
    private Book book2;

    private final List<BookCommentary> commentaries1 = new ArrayList<>();
    private final List<BookCommentary> commentaries2 = new ArrayList<>();

    @ChangeSet(order = "001", id = "dropDb", author = "romanvr", runAlways = true)
    public void dropDb(MongoDatabase db) {
        db.drop();
    }

    @ChangeSet(order = "002", id = "initAuthors", author = "romanvr")
    public void initAuthors(AuthorDao repository) {
        author = repository.save(new Author("ivan", "ivanov", Date.valueOf("2022-01-01"), "man"));
    }

    @ChangeSet(order = "003", id = "initGenre", author = "romanvr")
    public void initGenres(GenreDao repository) {
        genre = repository.save(new Genre("Java book"));
    }

    @ChangeSet(order = "004", id = "initBook", author = "romanvr")
    public void initBooks(BookDao repository) {
        book1 = repository.save(new Book("Java beginners", "text", author, genre));
        book2 = repository.save(new Book("Java professional", "text", author, genre));
    }

    @ChangeSet(order = "005", id = "initComments", author = "romanvr")
    public void initComments(BookCommentaryDao repository) {
        commentaries1.add(repository.save(new BookCommentary("Very good book", book1)));
        commentaries1.add(repository.save(new BookCommentary("not bed", book1)));
        commentaries1.add(repository.save(new BookCommentary("very well", book1)));

        commentaries2.add(repository.save(new BookCommentary("Very good book", book2)));
        commentaries2.add(repository.save(new BookCommentary("not bed", book2)));
        commentaries2.add(repository.save(new BookCommentary("very well", book2)));
    }

    @ChangeSet(order = "006", id = "addBooksToAuthors", author = "romanvr")
    public void addBooksToAuthors(AuthorDao repository) {
        author.getBookList().add(book1);
        author.getBookList().add(book2);
        repository.save(author);
    }

    @ChangeSet(order = "007", id = "addBooksToGenres", author = "romanvr")
    public void addBooksToGenres(GenreDao repository) {
        genre.getBookList().add(book1);
        genre.getBookList().add(book2);
        repository.save(genre);
    }

    @ChangeSet(order = "008", id = "addCommentariesToBook", author = "romanvr")
    public void addCommentariesToBook(BookDao repository) {
        book1.getBookCommentaries().addAll(commentaries1);
        repository.save(book1);
        book2.getBookCommentaries().addAll(commentaries2);
        repository.save(book2);
    }
}
