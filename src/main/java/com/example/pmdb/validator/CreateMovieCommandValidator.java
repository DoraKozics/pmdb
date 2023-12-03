package com.example.pmdb.validator;

import com.example.pmdb.dto.incoming.CreateMovieCommand;
import com.example.pmdb.service.MovieValidatorService;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class CreateMovieCommandValidator implements Validator {

    private final MovieValidatorService validatorService;

    public CreateMovieCommandValidator(MovieValidatorService validatorService) {
        this.validatorService = validatorService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return CreateMovieCommand.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if (target != null) {
            CreateMovieCommand createCommand = (CreateMovieCommand) target;

            if (createCommand.getTitle() == null || createCommand.getTitle().isBlank()) {
                errors.rejectValue("title", "createMovieCommand.title.empty");
            } else if (validatorService.checkIfAlreadyExists(createCommand.getTitle())) {
                errors.rejectValue("title", "createMovieCommand.title.notUnique");
            }

            if (createCommand.getDirector() == null || createCommand.getDirector().isBlank()) {
                errors.rejectValue("director", "createMovieCommand.director.empty");
            }

            if (createCommand.getYear() < 1900) {
                errors.rejectValue("year", "createMovieCommand.year.tooEarly");
            } else if (createCommand.getYear() > 2022) {
                errors.rejectValue("year", "createMovieCommand.year.tooLate");
            }

            if (createCommand.getGenres() != null && createCommand.getGenres().size() > 3) {
                errors.rejectValue("genres", "createMovieCommand.genres.invalid");
            }

            if (createCommand.getRating() == null) {
                errors.rejectValue("rating", "createMovieCommand.rating.empty");
            }

        }
    }
}
