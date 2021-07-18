package ru.otus.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.otus.spring.domain.H2Genre;

@Repository
public interface H2GenreRepository extends JpaRepository<H2Genre, String> {
}
