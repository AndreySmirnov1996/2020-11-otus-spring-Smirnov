package ru.otus.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.otus.spring.domain.H2Book;

import java.util.List;

@Repository
public interface H2BookRepository extends JpaRepository<H2Book, String> {

//    List<H2Book> saveAll(List<H2Book> entities);
}
