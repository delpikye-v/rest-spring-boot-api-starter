package com.dp.spring.template.services.interfaces.tutorial;

import com.dp.spring.template.models.tutorial.Tutorial;

import java.util.List;

public interface TutorialService {

    List<Tutorial> getAllTutorials(String title);

    Tutorial getTutorialById(long id);

    Tutorial getTutorialByIdAndTitle(long id, String title);

    Tutorial createTutorial(Tutorial tutorial);

    Tutorial updateTutorial(long id, Tutorial newTutorial);

    void deleteTutorial(long id);

    void deleteAllTutorials();

    List<Tutorial> findByPublished(boolean published);
}
