insert into genres (id, name) values (1, 'genre_1');
insert into genres (id, name) values (2, 'genre_2');
insert into genres (id, name) values (3, 'genre_3');

insert into authors (id, name, surname) values (1, 'author_name_1', 'author_surname_1');
insert into authors (id, name, surname) values (2, 'author_name_2', 'author_surname_2');
insert into authors (id, name, surname) values (3, 'author_name_3', 'author_surname_3');
insert into authors (id, name, surname) values (4, 'author_name_4', 'author_surname_4');

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
