package ru.itis.schedule.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itis.schedule.models.OptionalSubject;
import ru.itis.schedule.repositories.OptionalSubjectRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Override
    public Map<Integer, List<OptionalSubject>> getMap(Long courseId) {
        List<OptionalSubject> optionalSubjects = optionalSubjectRepository.getAllByCourse_Id(courseId);
        Map<Integer, List<OptionalSubject>> map = new HashMap<>();
        for(OptionalSubject optionalSubject : optionalSubjects){
            if (!map.containsKey(optionalSubject.getSet())){
                List<OptionalSubject> list = new ArrayList<>();
                list.add(optionalSubject);
                map.put(optionalSubject.getSet(), list);
            } else {
                map.get(optionalSubject.getSet()).add(optionalSubject);
            }
        }
        return map;
    }
}
