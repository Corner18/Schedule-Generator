package ru.itis.schedule.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "restriction")
public class Restriction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    // название ограничения
    private String name;
    // соблюдено ли ограничение
    private boolean enabled;
}
