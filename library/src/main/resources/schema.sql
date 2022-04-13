DROP TABLE IF EXISTS Author;
CREATE TABLE Author
(
    id          BIGINT PRIMARY KEY,
    name        VARCHAR(255) NOT NULL,
    family      VARCHAR(255) NOT NULL,
    dateOfBirth DATE         NOT NULL,
    gender      VARCHAR(255) NOT NULL,

    UNIQUE (name, family),
    CHECK (gender = 'man' or gender = 'woman')
);

DROP TABLE IF EXISTS Genre;
CREATE TABLE Genre
(
    id         BIGINT PRIMARY KEY,
    genreTitle VARCHAR(255) NOT NULL,

    UNIQUE (genreTitle)
);

DROP TABLE IF EXISTS Book;
CREATE TABLE Book
(
    id        BIGINT PRIMARY KEY,
    bookTitle VARCHAR(255) NOT NULL,
    author_id BIGINT       NOT NULL,
    genre_id  BIGINT       NOT NULL,

    FOREIGN KEY (author_id) REFERENCES Author (id),
    FOREIGN KEY (genre_id) REFERENCES Genre (id)
)
