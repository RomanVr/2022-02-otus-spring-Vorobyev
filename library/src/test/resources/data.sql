insert into Author (`name`, `lastName`, `dateBirth`, `gender`)
values ('ivan', 'ivanov', '2020-01-01', 'man'),
       ('max', 'maximov', '2020-02-02', 'man'),
       ('vika', 'zaikina', '2000-01-06', 'woman');

insert into Genre (`genreTitle`)
values ('Java book');

insert INTO Book (`bookTitle`, `preview`, `author_id`, `genre_id`)
values ('Java beginners', 'text', 1, 1),
       ('Java professional', 'text1', 1, 1),
       ('Java forever', 'text1', 1, 1);

insert into book_commentary (`commentary`, `book_id`)
values ('Very good book', 1),
       ('not bed', 1),
       ('very well', 1),
       ('Very good book', 2),
       ('not bed', 2),
       ('very well', 2),
       ('Very good book', 3),
       ('not bed', 3),
       ('very well', 3);

