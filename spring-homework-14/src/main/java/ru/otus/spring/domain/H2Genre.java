package ru.otus.spring.domain;

import lombok.*;

import javax.persistence.*;

@Table(name = "genres")
@Entity
@Data
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class H2Genre {

    @Id
    private String id;

    @Column(name = "name")
    private String name;
}
