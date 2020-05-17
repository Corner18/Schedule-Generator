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
@Table(name = "main_subject")

//таблица, объединяющая группу и обязательный предмет
public class MainSubject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "subject_set_id")
    private SubjectSet subjectSet;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;

}
