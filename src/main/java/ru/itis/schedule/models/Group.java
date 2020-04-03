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
@Table(name = "grouppa")

//список академ групп
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // номер группы
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;
    // кол-во студентов в группе
    private int count_of_students;
}
