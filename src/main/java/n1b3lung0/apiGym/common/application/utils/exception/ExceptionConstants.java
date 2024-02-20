package n1b3lung0.apiGym.common.application.utils.exception;

import lombok.experimental.UtilityClass;

@UtilityClass
public final class ExceptionConstants {

    // EXCEPTIONS

    // ID
    public static final String ID_REQUIRED = "Id is required";
    public static final String UUID_NOT_VALID = "'%s' is not a valid UUID";

    // EXERCISE
    public static final String EXERCISE_NOT_FOUND = "An exercise with ID '%s' could not be found";
    public static final String EXERCISE_NAME_LENGTH_NOT_VALID = "Name exercise must be between {min} and {max} characters long";
    public static final String EXERCISE_NOT_VALID = "Exercise '%s' is not valid. Reason: '%s'";
    public static final String EXERCISE_REPEATED = "An exercise with name %s has already been created";
    public static final String EXERCISE_INTENSITY_MIN = "Intensity of an exercise has to be minimum {value}";
    public static final String EXERCISE_INTENSITY_MAX = "Intensity of an exercise has to be maximum {value}";

    // CATEGORY
    public static final String CATEGORY_NOT_FOUND = "A category with ID '%s' could not be found";
    public static final String CATEGORY_NAME_LENGTH_NOT_VALID = "Name category must be between {min} and {max} characters long";
    public static final String CATEGORY_NOT_VALID = "Category '%s' is not valid. Reason: '%s'";
    public static final String CATEGORY_REPEATED = "A category with name %s has already been created";
}
