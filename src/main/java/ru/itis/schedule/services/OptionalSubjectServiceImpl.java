package ru.itis.schedule.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itis.schedule.models.OptionalSubject;
import ru.itis.schedule.repositories.OptionalSubjectRepository;

import java.util.List;

@Component
public class OptionalSubjectServiceImpl implements OptionalSubjectService {

    @Autowired
    private OptionalSubjectRepository optionalSubjectRepository;

    @Override
    public void save(OptionalSubject optionalSubject) {
        optionalSubjectRepository.save(optionalSubject);
    }

    @Override
    public List<OptionalSubject> getOptionalSubject() {
        return optionalSubjectRepository.findAll();
    }

    @Override
    public List<OptionalSubject> getOptionalSubjectByCourseId(Long courseId) {
        return optionalSubjectRepository.getAllByCourse_Id(courseId);
    }

    @Override
    public OptionalSubject getById(Long id) {
        return optionalSubjectRepository.getOne(id);
    }
}
