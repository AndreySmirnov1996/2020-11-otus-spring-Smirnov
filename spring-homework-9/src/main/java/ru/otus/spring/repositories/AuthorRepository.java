package ru.otus.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.otus.spring.domain.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
}
