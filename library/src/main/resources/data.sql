insert into Author (`name`, `family`, `dateOfBirth`, `gender`)
values ('ivan', 'ivanov', '2020-01-01', 'man');

insert into Genre (`genreTitle`)
values ('Java book');

insert INTO Book (`bookTitle`, `preview`, `author_id`, `genre_id`)
values ('Java beginners', 'text', 1, 1)
