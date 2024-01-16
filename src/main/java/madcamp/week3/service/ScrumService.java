package madcamp.week3.service;

import madcamp.week3.model.Scrum;
import madcamp.week3.repository.ScrumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScrumService {
    @Autowired
    private ScrumRepository scrumRepository;

    public List<Scrum> findScrumsByProjectId(Long projectId){
        return scrumRepository.findByProjectProjectId(projectId);
    }
}
