package ru.otus.spring.domain;

import lombok.*;

import javax.persistence.*;

@Table(name = "authors")
@Entity
@Data
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class H2Author {

    @Id
    private Long id;

    @Column(name = "name")
    private String name;
    @Column(name = "surname")
    private String surname;
}
