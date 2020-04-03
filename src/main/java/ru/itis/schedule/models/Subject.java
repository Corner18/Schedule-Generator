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
@Table(name = "subject")

//список обязательный предметов
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // название предмета
    private String title;
    // курс, на котором приподается предмет
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;
}
