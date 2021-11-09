DROP SEQUENCE IF EXISTS AUTHORS_SEQUENCE;
CREATE SEQUENCE AUTHORS_SEQUENCE START WITH 1 INCREMENT BY 1;

drop table if exists authors cascade;
create table authors
(
    id      bigint UNIQUE primary key,
    name    varchar(255),
    surname varchar(255)
);


drop table if exists genres cascade;
create table genres
(
    id   bigint UNIQUE primary key,
    name varchar(255)
);

DROP SEQUENCE IF EXISTS BOOKS_SEQUENCE;
CREATE SEQUENCE BOOKS_SEQUENCE START WITH 1 INCREMENT BY 1;

drop table if exists books cascade;
create table books
(
    id       bigint UNIQUE primary key,
    title    varchar(255),
    genre_id bigint references genres (id)
);

drop table if exists authors_books;
create table authors_books
(
    author_id bigint references authors (id) on delete cascade,
    book_id   bigint references books (id) on delete cascade,
    primary key (author_id, book_id)
);

DROP SEQUENCE IF EXISTS COMMENTS_SEQUENCE;
CREATE SEQUENCE COMMENTS_SEQUENCE START WITH 1 INCREMENT BY 1;

drop table if exists comments;
create table comments
(
    id      bigint UNIQUE primary key,
    text    varchar(255),
    book_id bigint references books (id) on delete cascade
);
