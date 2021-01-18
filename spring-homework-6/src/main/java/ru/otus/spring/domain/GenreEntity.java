package ru.otus.spring.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Table(name = "genres")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GenreEntity {

    public GenreEntity(long id) {
        this.id = id;
        this.name = null;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;
}
