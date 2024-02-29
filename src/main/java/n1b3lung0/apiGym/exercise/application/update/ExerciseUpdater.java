package n1b3lung0.apiGym.exercise.application.update;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import n1b3lung0.apiGym.category.domain.Category;
import n1b3lung0.apiGym.category.domain.CategoryRepository;
import n1b3lung0.apiGym.category.domain.exception.CategoryNotFound;
import n1b3lung0.apiGym.common.application.utils.exception.ExceptionConstants;
import n1b3lung0.apiGym.common.application.utils.log.LogConstants;
import n1b3lung0.apiGym.exercise.application.find.ExerciseFinder;
import n1b3lung0.apiGym.exercise.domain.Exercise;
import n1b3lung0.apiGym.exercise.domain.ExerciseChange;
import n1b3lung0.apiGym.exercise.domain.ExerciseRepository;
import n1b3lung0.apiGym.exercise.domain.Image;
import n1b3lung0.apiGym.exercise.domain.Video;
import n1b3lung0.apiGym.exercise.domain.exception.ExerciseNotValid;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.SetUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class ExerciseUpdater {

    private final ExerciseFinder finder;
    private final ExerciseRepository repository;
    private final CategoryRepository categoryRepository;

    private static final String NAME_FIELD = "name";
    private static final String DESCRIPTION_FIELD = "description";
    private static final String IMAGE_FIELD = "image";
    private static final String VIDEO_FIELD = "video";
    private static final String INTENSITY_FIELD = "intensity";
    private static final String CATEGORIES_FIELD = "categories";

    @Transactional
    public Exercise updateFields(ExerciseUpdateRequest request) {

        List<ExerciseChange> changes = new ArrayList<>();
        Exercise exercise = finder.findById(request.getId());

        String name = request.getName();
        if (StringUtils.isNotBlank(name) && !StringUtils.equals(name, exercise.getName())) {
            if (repository.findByName(name).isPresent()) {
                throw new ExerciseNotValid(name, String.format(ExceptionConstants.EXERCISE_REPEATED, name));
            }
            exercise = exercise.withName(name);
            changes.add(ExerciseChange.create(NAME_FIELD, exercise.getName()));
        }

        String description = request.getDescription();
        if (description != null && !StringUtils.equals(description, exercise.getDescription())) {
            exercise = exercise.withDescription(description);
            changes.add(ExerciseChange.create(DESCRIPTION_FIELD, exercise.getDescription()));
        }

        Image image = request.getImage();
        if (image != null && ObjectUtils.notEqual(image, exercise.getImage())) {
            exercise = exercise.withImage(image);
            changes.add(ExerciseChange.create(IMAGE_FIELD, exercise.getImage()));
        }

        Video video = request.getVideo();
        if (video != null && ObjectUtils.notEqual(video, exercise.getVideo())) {
            exercise = exercise.withVideo(video);
            changes.add(ExerciseChange.create(VIDEO_FIELD, exercise.getVideo()));
        }

        Integer intensity = request.getIntensity();
        if (intensity != null && !intensity.equals(exercise.getIntensity())) {
            exercise = exercise.withIntensity(intensity);
            changes.add(ExerciseChange.create(INTENSITY_FIELD, exercise.getIntensity()));
        }

        Set<String> newCategoryIds = request.getCategoryIds();
        if (newCategoryIds != null && !SetUtils.isEqualSet(newCategoryIds, categoryIds(exercise.getCategories()))) {
            exercise = exercise.withCategories(categories(newCategoryIds));
            changes.add(ExerciseChange.create(CATEGORIES_FIELD, exercise.getCategories()));
        }

        if (CollectionUtils.isNotEmpty(changes)) {
            exercise = repository.save(exercise);
            log.debug(String.format(LogConstants.EXERCISE_UPDATED, exercise));
        }

        return exercise;
    }

    private Set<String> categoryIds(Set<Category> categories) {
        return categories.stream()
                .map(category -> category.getId().toString())
                .collect(Collectors.toSet());
    }

    private Set<Category> categories(Set<String> categoryIds) {
        return categoryIds.stream()
                .map(id -> categoryRepository.findByIdAndDeletedFalse(UUID.fromString(id)).orElseThrow(() -> new CategoryNotFound(id)))
                .collect(Collectors.toSet());
    }
}
