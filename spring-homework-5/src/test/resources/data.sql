delete from books;
delete from authors;
delete from genres;

insert into genres (id, `name`) values (11, 'genre_1');

insert into authors (id, `name`, surname) values (11, 'author_name_1', 'author_surname_1');
insert into authors (id, `name`, surname) values (22, 'author_name_2', 'author_surname_2');

insert into books (id, title, genre_id) values (111, 'book_name_1', 11);

insert into authors_books (author_id, book_id) values (11, 111);
insert into authors_books (author_id, book_id) values (22, 111);