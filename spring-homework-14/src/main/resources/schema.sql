drop table if exists authors;
create table authors
(
    id      varchar2(24) IDENTITY primary key,
    name    varchar2(255),
    surname varchar2(255),
    phone   varchar2(20)
);


drop table if exists genres;
create table genres
(
    id   varchar2(24) IDENTITY primary key,
    name varchar2(255)
);


drop table if exists books;
create table books
(
    id       varchar2(24) IDENTITY primary key,
    title    varchar2(255),
    genre_id varchar2(24) references genres (id)
);


drop table if exists authors_books;
create table authors_books
(
    author_id varchar2(24) references authors (id) on delete cascade,
    book_id   varchar2(24) references books (id) on delete cascade,
    primary key (author_id, book_id)
);