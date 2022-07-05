package com.dp.spring.template.controllers.tutorial;

import com.dp.spring.template.common.UserContext;
import com.dp.spring.template.controllers.base.BaseController;
import com.dp.spring.template.dtos.tutorial.TutorialDto;
import com.dp.spring.template.services.implement.tutorial.TutorialServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/*@CrossOrigin(origins = "http://localhost:8081")*/
@Validated
@RestController
@RequestMapping("/tutorials")
public class TutorialController extends BaseController {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    TutorialServiceImpl tutorialService;

    @GetMapping("")
    public ResponseEntity<List<TutorialDto>> getAllTutorials(@RequestParam(required = false) String title) {
        UserContext userContext = getUserContext();
        List<TutorialDto> tutorialResponse = tutorialService.getAllTutorials(title)
                .stream().map(tutorial -> modelMapper.map(tutorial, TutorialDto.class)).collect(Collectors.toList());

        if (tutorialResponse.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(tutorialResponse, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TutorialDto> getTutorialById(@PathVariable("id") long id) {
        return new ResponseEntity<>(
                modelMapper.map(tutorialService.getTutorialById(id), TutorialDto.class),
                HttpStatus.OK
        );
    }

    @GetMapping("/id-title/{id}")
    public ResponseEntity<TutorialDto> getTutorialByIdAndTitle(
            @PathVariable("id") long id,
            @RequestParam(required = false) String title
    ) {
        return new ResponseEntity<>(
                modelMapper.map(tutorialService.getTutorialByIdAndTitle(id, title), TutorialDto.class),
                HttpStatus.OK
        );
    }

    @PostMapping("")
    public ResponseEntity<TutorialDto> createTutorial(@Valid @RequestBody TutorialDto tutorialDto) {
        return new ResponseEntity<>(
                modelMapper.map(tutorialService.createTutorial(tutorialDto.toTutorial()), TutorialDto.class),
                HttpStatus.CREATED
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<TutorialDto> updateTutorial(
            @PathVariable("id") long id,
            @RequestBody TutorialDto tutorialDto
    ) {
        return new ResponseEntity<>(
                modelMapper.map(tutorialService.updateTutorial(id, tutorialDto.toTutorial()), TutorialDto.class),
                HttpStatus.OK
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteTutorial(@PathVariable("id") long id) {
        tutorialService.deleteTutorial(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("")
    public ResponseEntity<HttpStatus> deleteAllTutorials() {
        tutorialService.deleteAllTutorials();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    @GetMapping("/publish")
    public ResponseEntity<List<TutorialDto>> findByPublished(@RequestParam(required = false) boolean value) {
        List<TutorialDto> tutorialResponse = tutorialService.findByPublished(value)
                .stream().map(tutorial -> modelMapper.map(tutorial, TutorialDto.class)).collect(Collectors.toList());

        if (tutorialResponse.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(tutorialResponse, HttpStatus.OK);
    }

}
