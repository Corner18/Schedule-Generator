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
@Table(name = "exam")

//итоговая таблица
public class Exam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //основной предмет (если есть)
    @ManyToOne
    @JoinColumn(name = "main_subject_id")
    private MainSubject mainSubject;
    //курс по выбору (если есть)
    @ManyToOne
    @JoinColumn(name = "optional_subject_id")
    private OptionalSubject optionalSubject;
    //препод
    @ManyToOne
    @JoinColumn(name = "professor_id")
    private Professor professor;
    //аудитория
    @ManyToOne
    @JoinColumn(name = "auditory_id")
    private Auditory auditory;
    //время
    @ManyToOne
    @JoinColumn(name = "timeslot_id")
    private Timeslot timeslot;

    @ManyToOne
    @JoinColumn(name = "schedule_id")
    private Schedule schedule;
}
