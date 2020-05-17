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
@Table(name = "timeslot")

//список возможных временных слотов для экзамена
public class Timeslot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    //день
    @ManyToOne
    @JoinColumn(name = "timeslot_day_id")
    private TimeslotDay timeslotDay;
    //время
    @ManyToOne
    @JoinColumn(name = "timeslot_time_id")
    private TimeslotTime timeslotTime;
}
