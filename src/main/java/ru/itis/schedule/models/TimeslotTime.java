package ru.itis.schedule.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "timeslot_time")

//возможное время начала экзамена (дважды в день в 9:00 и 15:00.
// На каждый экзамен отводится по 6 часов
public class TimeslotTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //время начала экзамена
    private LocalTime time;
}
