package ru.itis.schedule.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.schedule.models.Exam;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExamDto {
    private int auditory;
    private String subject;
    private LocalDate date;
    private LocalTime time;
    private String group;
    private String professor;

    public static ExamDto from(Exam exam) {
        if(exam.getMainSubject()!= null){
            return ExamDto.builder()
                    .auditory(exam.getAuditory().getNumber())
                    .subject(exam.getMainSubject().getSubjectSet().getSubject().getTitle())
                    .date(exam.getTimeslot().getTimeslotDay().getDate())
                    .time(exam.getTimeslot().getTimeslotTime().getTime())
                    .group(exam.getMainSubject().getGroup().getNumber())
                    .professor(exam.getProfessor().getName())
                    .build();
        } else if(exam.getOptionalSubject() != null){
            return ExamDto.builder()
                    .auditory(exam.getAuditory().getNumber())
                    .subject(exam.getOptionalSubject().getTitle())
                    .date(exam.getTimeslot().getTimeslotDay().getDate())
                    .time(exam.getTimeslot().getTimeslotTime().getTime())
                    .professor(exam.getProfessor().getName())
                    .build();
        }
        return null;
    }

    public static List<ExamDto> from(List<Exam> exams) {
        return exams.stream()
                .map(ExamDto::from)
                .collect(Collectors.toList());
    }
}
