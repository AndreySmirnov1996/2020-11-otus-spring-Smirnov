package ru.otus.spring.repositories;

import ru.otus.spring.domain.AuthorBookRelation;

import java.util.List;

public interface AuthorBookRelationRepository {

    void save(AuthorBookRelation authorBookRelation);

    List<AuthorBookRelation> findAuthorBookRelationsByBookId(long bookId);

    List<AuthorBookRelation> findAuthorBookRelationsByAuthorId(long authorId);

    void deleteRelationsByBookId(long bookId);
}
