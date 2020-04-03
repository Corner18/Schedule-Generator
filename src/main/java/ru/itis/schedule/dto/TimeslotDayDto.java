package ru.itis.schedule.dto;

import lombok.*;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class TimeslotDayDto {
    LocalDate begin;
    LocalDate end;
}
