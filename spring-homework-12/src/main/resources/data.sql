insert into genres (id, `name`) values (1, 'genre_1');
insert into genres (id, `name`) values (2, 'genre_2');
insert into genres (id, `name`) values (3, 'genre_3');

insert into authors (id, `name`, surname) values (1, 'author_name_1', 'author_surname_1');
insert into authors (id, `name`, surname) values (2, 'author_name_2', 'author_surname_2');
insert into authors (id, `name`, surname) values (3, 'author_name_3', 'author_surname_3');
insert into authors (id, `name`, surname) values (4, 'author_name_4', 'author_surname_4');

insert into books (id, title, genre_id) values (1, 'book_name_1', 1);
insert into books (id, title, genre_id) values (2, 'book_name_2', 1);

insert into authors_books (author_id, book_id) values (1, 1);
insert into authors_books (author_id, book_id) values (2, 1);
insert into authors_books (author_id, book_id) values (2, 2);
insert into authors_books (author_id, book_id) values (3, 2);
insert into authors_books (author_id, book_id) values (4, 2);

insert into comments (id, text, book_id) values (1, 'text1', 1);
insert into comments (id, text, book_id) values (2, 'text2', 1);
insert into comments (id, text, book_id) values (3, 'text3', 2);

-- Add user
insert into users (id, role, login, password_hash, enabled) values (1, 'ADMIN', 'admin', '$2a$10$lqj04iq3qH7khXJF6x3.YeLF4L1hAUPYaAkau.NLVJjGGV3txPgfu', true); --password = admin
insert into users (id, role, login, password_hash, enabled) values (2, 'USER', 'user', '$2a$10$bYQtsnxTSzqZNn7J2JtJj.j7cF3mjJvPTi6JBMzk65NpVi8XBvch2', true); -- password = user