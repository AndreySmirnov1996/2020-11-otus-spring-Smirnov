package ru.otus.spring.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Table(name = "authors")
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Author {

    @Id
    @SequenceGenerator(name = "authorsSequence", sequenceName = "AUTHORS_SEQUENCE", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "authorsSequence")
    private long id;

    @Column(name = "name")
    private String name;
    @Column(name = "surname")
    private String surname;
}
