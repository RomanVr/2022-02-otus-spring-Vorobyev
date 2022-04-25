insert into Author (`name`, `lastName`, `dateOfBirth`, `gender`)
values ('ivan', 'ivanov', '2020-01-01', 'man'),
       ('max', 'maximov', '2020-02-02', 'man'),
       ('vika', 'zaikina', '2000-01-06', 'woman');

insert into Genre (`genreTitle`)
values ('Java book');

insert INTO Book (`bookTitle`, `preview`, `author_id`, `genre_id`)
values ('Java beginners', 'text', 1, 1)
