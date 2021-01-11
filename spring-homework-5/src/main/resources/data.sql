insert into genres (id, `name`) values (1, 'genre_1');

insert into authors (id, `name`, surname, phone) values (1, 'author_name_1', 'author_surname_1', '880055535351');
insert into authors (id, `name`, surname, phone) values (2, 'author_name_2', 'author_surname_2', '880055535352');
insert into authors (id, `name`, surname, phone) values (3, 'author_name_3', 'author_surname_3', '880055535353');
insert into authors (id, `name`, surname, phone) values (4, 'author_name_4', 'author_surname_4', '880055535354');

insert into books (id, title, cost, genre_id) values (1, 'book_name_1', 99.99, 1);
insert into books (id, title, cost, genre_id) values (2, 'book_name_2', 1.99, 1);

insert into authors_books (author_id, book_id) values (1, 1);
insert into authors_books (author_id, book_id) values (2, 1);
insert into authors_books (author_id, book_id) values (2, 2);
insert into authors_books (author_id, book_id) values (3, 2);
insert into authors_books (author_id, book_id) values (4, 2);