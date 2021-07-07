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
public class H2Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column(name = "name")
    private String name;
    @Column(name = "surname")
    private String surname;
}
