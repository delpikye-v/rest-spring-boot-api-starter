package com.dp.spring.template.dtos.tutorial;

import com.dp.spring.template.models.tutorial.Tutorial;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class TutorialDto {
    private long id;

    @NotBlank(message = "The title is required.")
    private String title;

    private String description;

    private boolean published;

    public Tutorial toTutorial() {
        return new Tutorial(title, description, published);
    }
}
