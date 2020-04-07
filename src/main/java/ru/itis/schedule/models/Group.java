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
    // курс группы
    @ManyToOne
    @JoinColumn(name = "group_set_id")
    private GroupSet groupSet;
    // кол-во студентов в группе
    private Integer count_of_students;
    //номер группы
    private String number;
}
