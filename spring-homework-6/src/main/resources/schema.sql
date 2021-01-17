drop table if exists authors;
create table authors
(
    id      bigint primary key,
    name    varchar2(255),
    surname varchar2(255),
    phone   varchar2(20)
);


drop table if exists genres;
create table genres
(
    id   bigint primary key,
    name varchar2(255)
);


drop table if exists books;
create table books
(
    id       bigint primary key,
    title    varchar2(255),
    cost     varchar2(255),
    genre_id bigint references genres (id)
);


drop table if exists authors_books;
create table authors_books
(
    author_id bigint references authors (id),
    book_id   bigint references books (id),
    primary key (author_id, book_id)
);