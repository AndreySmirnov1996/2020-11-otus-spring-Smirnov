package ru.otus.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.otus.spring.domain.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    @Modifying
    @Query("update Book b set b.title = :title where b.id = :id")
    void updateTitleById(@Param("id") Long id, @Param("title") String title);
}
