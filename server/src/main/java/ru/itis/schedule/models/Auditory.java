package ru.itis.schedule.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "auditory")

//список аудиторий
public class Auditory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    //номер аудиории
    private int number;
    //вместимость аудитории
    private int capasity;

}
