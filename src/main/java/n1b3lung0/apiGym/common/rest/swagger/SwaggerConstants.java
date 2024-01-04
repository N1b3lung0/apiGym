package n1b3lung0.apiGym.common.rest.swagger;

import lombok.experimental.UtilityClass;

@UtilityClass
public final class SwaggerConstants {

    public static final String OK_CODE = "200";
    public static final String OK_DESCRIPTION = "OK. Request has been successfully fulfilled";

    public static final String CREATED_CODE = "201";
    public static final String CREATED_DESCRIPTION = "CREATED. Resource has been succesfully created";

    public static final String NO_CONTENT_CODE = "204";
    public static final String NO_CONTENT_DESCRIPTION = "NO CONTENT. Request has been successfully fulfilled. No content has been retrieved";

    public static final String BAD_REQUEST_CODE = "400";
    public static final String BAD_REQUEST_DESCRIPTION = "BAD REQUEST. Request with errors";

    public static final String UNAUTHORIZED_CODE = "401";
    public static final String UNAUTHORIZED_DESCRIPTION = "UNAUTHORIZED. Request is not authorized";

    public static final String FORBIDDEN_CODE = "403";
    public static final String FORBIDDEN_DESCRIPTION = "FORBIDDEN. Request is not allowed";

    public static final String NOT_FOUND_CODE = "404";
    public static final String NOT_FOUND_DESCRIPTION = "NOT FOUND. Resource could not be found";

    public static final String SERVER_ERROR_CODE = "500";
    public static final String SERVER_ERROR_DESCRIPTION = "INTERNAL SERVER ERROR. Request could not be fulfilled due to an unexpected error";
}
