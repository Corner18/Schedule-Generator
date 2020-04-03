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
@Table(name = "auditory_resource")
//временные возможности аудиторий
public class AuditoryResource {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "auditory_id")
    private Auditory auditory;
    @ManyToOne
    @JoinColumn(name = "timeslot_id")
    private Timeslot timeslot;
}
