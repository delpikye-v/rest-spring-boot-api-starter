package com.dp.spring.template.services.implement.tutorial;

import com.dp.spring.template.services.interfaces.tutorial.TutorialService;
import com.dp.spring.template.websocket.DefaultWebSocketHandler;
import com.dp.spring.template.exceptions.NotFoundException;
import com.dp.spring.template.models.tutorial.Tutorial;
import com.dp.spring.template.repositories.tutorial.TutorialRepository;
import com.dp.spring.template.services.implement.base.BaseService;
import com.dp.spring.template.websocket.WebSocketMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static com.dp.spring.template.websocket.WebSocketMessage.Type.TUTORIAL;

@Service
public class TutorialServiceImpl extends BaseService implements TutorialService {
    @Autowired
    TutorialRepository tutorialRepository;

    @Autowired
    DefaultWebSocketHandler defaultWebSocketHandler;

    /*private final TutorialRepository tutorialRepository;

    public TutorialServiceImpl(TutorialRepository tutorialRepository) {
        this.tutorialRepository = tutorialRepository;
    }*/

    public List<Tutorial> getAllTutorials(String title) {
        // Example websocket
        try {
            String broadcast = "server periodic message " + LocalTime.now();
            defaultWebSocketHandler.sendWebSocketMessages(new WebSocketMessage(TUTORIAL, broadcast));
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }

        List<Tutorial> tutorials = new ArrayList<Tutorial>();

        if (title == null)
            tutorialRepository.findAll().forEach(tutorials::add);
        else
            tutorialRepository.findByTitleContaining(title).forEach(tutorials::add);

        return tutorials;
    }

    public Tutorial getTutorialById(long id) {
        return tutorialRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Not found Tutorial with id = " + id));
    }

    public Tutorial getTutorialByIdAndTitle(long id, String title) {
        return tutorialRepository.findByIdAndTitleContaining(id, title)
                .orElseThrow(() -> new NotFoundException("Not found Tutorial with id = " + id + " - title = " + title));
    }

    public Tutorial createTutorial(Tutorial tutorial) {
        return tutorialRepository
                .save(new Tutorial(tutorial.getTitle(), tutorial.getDescription(), tutorial.isPublished()));
    }

    public Tutorial updateTutorial(long id, Tutorial newTutorial) {
        Tutorial tutorial = tutorialRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Not found Tutorial with id = " + id));

        tutorial.setTitle(newTutorial.getTitle());
        tutorial.setDescription(newTutorial.getDescription());
        tutorial.setPublished(newTutorial.isPublished());
        return tutorialRepository.save(tutorial);
    }

    public void deleteTutorial(long id) {
        tutorialRepository.deleteById(id);
    }

    public void deleteAllTutorials() {
        tutorialRepository.deleteAll();
    }

    public List<Tutorial> findByPublished(boolean published) {
        return tutorialRepository.findByPublished(published);
    }
}
