insert into Author (`name`, `lastName`, `dateOfBirth`, `gender`)
values ('ivan', 'ivanov', '2020-01-01', 'man');

insert into Genre (`genreTitle`)
values ('Java book');

insert INTO Book (`bookTitle`, `preview`, `author_id`, `genre_id`)
values ('Java beginners', 'text', 1, 1),
       ('Java professional', 'text1', 1, 1)
