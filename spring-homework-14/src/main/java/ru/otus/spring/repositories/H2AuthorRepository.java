package ru.otus.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.otus.spring.domain.H2Author;

@Repository
public interface H2AuthorRepository extends JpaRepository<H2Author, String> {
}
