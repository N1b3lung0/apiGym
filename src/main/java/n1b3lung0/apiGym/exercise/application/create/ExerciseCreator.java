package n1b3lung0.apiGym.exercise.application.create;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import n1b3lung0.apiGym.category.domain.Category;
import n1b3lung0.apiGym.category.domain.CategoryRepository;
import n1b3lung0.apiGym.category.domain.exception.CategoryNotFound;
import n1b3lung0.apiGym.common.application.utils.log.LogConstants;
import n1b3lung0.apiGym.exercise.domain.Exercise;
import n1b3lung0.apiGym.exercise.domain.ExerciseRepository;
import n1b3lung0.apiGym.exercise.domain.exception.ExerciseAlreadyExists;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class ExerciseCreator {

    private final ExerciseRepository repository;
    private final CategoryRepository categoryRepository;

    @Transactional
    public Exercise create(ExerciseCreateRequest request) {
        String name = request.getName();
        if (repository.findByName(name).isPresent()) {
            throw new ExerciseAlreadyExists(name);
        }

        Exercise exercise = repository.save(request.toExercise(
                name,
                request.getDescription(),
                request.getImage(),
                request.getVideo(),
                request.getIntensity()
                )
        );
        Exercise withCategories = (request.getCategoryIds() != null)
                ? addCategories(exercise, request.getCategoryIds())
                : exercise;
        Exercise created = repository.save(withCategories);
        log.debug(String.format(LogConstants.EXERCISE_CREATED, created));
        return created;
    }

    private Exercise addCategories(Exercise exercise, Set<String> categoryIds) {
        Set<Category> categories = categoryIds.stream()
                .map(id -> categoryRepository.findByIdAndDeletedFalse(UUID.fromString(id))
                        .orElseThrow(() -> new CategoryNotFound(id)))
                .collect(Collectors.toSet());
        return exercise.withCategories(categories);
    }
}
