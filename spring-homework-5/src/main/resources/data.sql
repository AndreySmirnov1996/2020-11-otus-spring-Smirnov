insert into genres (`name`) values ('genre_1');

insert into authors (`name`, surname) values ('author_name_1', 'author_surname_1');
insert into authors (`name`, surname) values ('author_name_2', 'author_surname_2');
insert into authors (`name`, surname) values ('author_name_3', 'author_surname_3');
insert into authors (`name`, surname) values ('author_name_4', 'author_surname_4');

insert into books (title, genre_id) values ('book_name_1', 1);
insert into books (title, genre_id) values ('book_name_2', 1);

insert into authors_books (author_id, book_id) values (1, 1);
insert into authors_books (author_id, book_id) values (2, 1);
insert into authors_books (author_id, book_id) values (2, 2);
insert into authors_books (author_id, book_id) values (3, 2);
insert into authors_books (author_id, book_id) values (4, 2);