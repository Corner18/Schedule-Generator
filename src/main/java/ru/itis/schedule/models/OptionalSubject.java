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
@Table(name = "optional_subject")

//таблица предметов по выбору
public class OptionalSubject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //название предмета
    private String title;
    //номер курса, на котором присутсвует этот предмет
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;
    //кол-во учеников на данном предмете
    private int count_of_students;
}
