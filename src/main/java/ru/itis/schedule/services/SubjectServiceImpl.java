package ru.itis.schedule.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itis.schedule.models.Subject;
import ru.itis.schedule.repositories.SubjectRepository;

import java.util.List;

@Component
public class SubjectServiceImpl implements SubjectService {

    @Autowired
    private SubjectRepository subjectRepository;

    @Override
    public void save(Subject subject) {
        subjectRepository.save(subject);
    }

    @Override
    public List<Subject> getSubjects() {
        return subjectRepository.findAll();
    }

    @Override
    public List<Subject> getSubjectsByCourseId(Long courseId) {
        return subjectRepository.getAllByCourse_Id(courseId);
    }
}
