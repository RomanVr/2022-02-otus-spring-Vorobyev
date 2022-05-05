DROP TABLE IF EXISTS Author;
CREATE TABLE Author
(
    id        BIGINT PRIMARY KEY AUTO_INCREMENT,
    name      VARCHAR(255) NOT NULL,
    lastName  VARCHAR(255) NOT NULL,
    dateBirth DATE         NOT NULL,
    gender    VARCHAR(255) NOT NULL,

    UNIQUE (name, lastName),
    CHECK (gender = 'man' or gender = 'woman')
);

DROP TABLE IF EXISTS Genre;
CREATE TABLE Genre
(
    id         BIGINT PRIMARY KEY AUTO_INCREMENT,
    genreTitle VARCHAR(255) NOT NULL,

    UNIQUE (genreTitle)
);

DROP TABLE IF EXISTS Book;
CREATE TABLE Book
(
    id        BIGINT PRIMARY KEY AUTO_INCREMENT,
    bookTitle VARCHAR(255) NOT NULL,
    preview   VARCHAR(255) NOT NULL,
    author_id BIGINT       NOT NULL,
    genre_id  BIGINT       NOT NULL,

    FOREIGN KEY (author_id) REFERENCES Author (id),
    FOREIGN KEY (genre_id) REFERENCES Genre (id),

    UNIQUE (bookTitle)
);

DROP TABLE IF EXISTS book_commentary;
CREATE TABLE book_commentary
(
    id         BIGINT PRIMARY KEY AUTO_INCREMENT,
    commentary VARCHAR(255) NOT NULL,
    book_id    BIGINT       NOT NULL,

    FOREIGN KEY (book_id) REFERENCES Book
);
