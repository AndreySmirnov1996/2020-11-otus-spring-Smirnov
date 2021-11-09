package ru.otus.spring.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Table(name = "genres")
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Genre {

    @Id
    private long id;

    @Column(name = "name")
    private String name;
}
