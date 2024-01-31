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
    public static final String EXERCISE_NAME_LENGTH_NOT_VALID = "Name exercise must not be more than {max} characters long";
    public static final String EXERCISE_NOT_VALID = "Exercise '%s' is not valid. Reason: '%s'";
    public static final String EXERCISE_REPEATED = "An exercise with name %s has already been created";
    public static final String EXERCISE_INTENSITY_MIN = "Intensity of an exercise has to be minimum {value}";
    public static final String EXERCISE_INTENSITY_MAX = "Intensity of an exercise has to be maximum {value}";
}
