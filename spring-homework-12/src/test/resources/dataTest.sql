insert into genres (id, `name`) values (11, 'genre_1');

insert into authors (id, `name`, surname) values (11, 'author_name_1', 'author_surname_1');
insert into authors (id, `name`, surname) values (22, 'author_name_2', 'author_surname_2');

insert into books (id, title, genre_id) values (111, 'book_name_1', 11);

insert into authors_books (author_id, book_id) values (11, 111);
insert into authors_books (author_id, book_id) values (22, 111);

insert into comments (id, text, book_id) values (7, 'Good book!', 111);

insert into users (id, role, login, password_hash, enabled) values (1, 'ROLE_ADMIN', 'admin', '$2a$10$lqj04iq3qH7khXJF6x3.YeLF4L1hAUPYaAkau.NLVJjGGV3txPgfu', true); --password = admin