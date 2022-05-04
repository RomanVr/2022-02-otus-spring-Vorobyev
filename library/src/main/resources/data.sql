insert into Author (`name`, `lastName`, `dateBirth`, `gender`)
values ('ivan', 'ivanov', '2020-01-01', 'man');

insert into Genre (`genreTitle`)
values ('Java book');

insert INTO Book (`bookTitle`, `preview`, `author_id`, `genre_id`)
values ('Java beginners', 'text', 1, 1),
       ('Java professional', 'text1', 1, 1);

insert into book_commentary (`commentary`, `book_id`)
values ('Very good book', 1),
       ('not bed', 1),
       ('very well', 1);
