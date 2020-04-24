package ru.itis.schedule.services;

import ru.itis.schedule.dto.ExamDto;
import ru.itis.schedule.models.Exam;
import ru.itis.schedule.models.Group;
import ru.itis.schedule.models.ProfessorResource;

import java.util.List;

public interface ExamService {
    List<Exam> getExams();

    void save(Exam exam);

    List<Exam> getExamsByGroup(Group group);

    List<Exam> getAllByMainSubject_Group_Id(Long groupId);

    List<Exam> getAllByOptionalSubject_Course_Id(Long courseId);

    List<ExamDto> getExamsDto();

    List<Exam> getExamsByProfessorResource(ProfessorResource professorResource);

    void update(Exam exam);
}
