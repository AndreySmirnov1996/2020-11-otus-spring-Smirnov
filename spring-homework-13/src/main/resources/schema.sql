drop table if exists authors;
create table authors
(
    id      bigint IDENTITY primary key,
    name    varchar2(255),
    surname varchar2(255),
    phone   varchar2(20)
);


drop table if exists genres;
create table genres
(
    id   bigint IDENTITY primary key,
    name varchar2(255)
);


drop table if exists books;
create table books
(
    id       bigint IDENTITY primary key,
    title    varchar2(255),
    genre_id bigint references genres (id)
);


drop table if exists authors_books;
create table authors_books
(
    author_id bigint references authors (id) on delete cascade,
    book_id   bigint references books (id) on delete cascade,
    primary key (author_id, book_id)
);

drop table if exists comments;
create table comments
(
    id      bigint IDENTITY primary key,
    text    varchar2(255),
    book_id bigint references books (id) on delete cascade
);

drop table if exists users;
create table users
(
    id       bigint IDENTITY primary key,
    role     varchar2(255) not null,
    login    varchar2(255) not null,
    password_hash varchar2(300) not null,
    enabled  boolean not null
);