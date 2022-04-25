DROP TABLE IF EXISTS Author;
CREATE TABLE Author
(
    id          BIGINT PRIMARY KEY AUTO_INCREMENT,
    name        VARCHAR(255) NOT NULL,
    lastName    VARCHAR(255) NOT NULL,
    dateOfBirth DATE         NOT NULL,
    gender      VARCHAR(255) NOT NULL,

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
    preview   TEXT         NOT NULL,
    author_id BIGINT       NOT NULL,
    genre_id  BIGINT       NOT NULL,

    FOREIGN KEY (author_id) REFERENCES Author (id),
    FOREIGN KEY (genre_id) REFERENCES Genre (id),

    UNIQUE (bookTitle)
)
