package ru.itis.schedule.services;

import ru.itis.schedule.models.*;

import java.util.List;

public interface RestrictionService {

    /*
    здесь будут представлены следующие ограничения
    1. В один временной отрезок может проводится только один экзамен в одной аудитории
    2. В один временной отрезок у преподавателя может быть экзаменов не больше, чем сколько он считает нужным
    (здесь была попытка учесть пожелание преподавателей проводить экзамен у нескольких групп одновременно)
    3. У каждой группы минимальный период между экзаменами -  x дня
    4. Количество мест в аудитории больше или равно количеству студентов
    5. У преподов экзамены только тогда, когда они могут
    (к курсам по выбору не относится иначе смэрть) ну ща посмотрим как пойдет
    6. Все предметы по выбору в один день на каждом курсе
     */

    // проверка ограничения 1
    boolean isOneExamInAuditory(List<Exam> exams);

    // проверка ограничения 2
    boolean isLimitedExamsAtProfessor(List<Exam> exams);

    // проверка ограничения 3
    boolean isGapBetweenExams(List<Exam> exams, int gap);

    // проверка ограничения 4
    boolean isAuditoryCapasityBigerStidentCount(List<Exam> exams);

    // проверка ограничения 5
    boolean isProfessorsCan(List<Exam> exams);

    // проверка ограничения 6
    boolean isOptionalSubjectsInOneDay(List<Exam> exams);

}
