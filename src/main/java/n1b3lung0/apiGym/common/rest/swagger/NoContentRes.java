package n1b3lung0.apiGym.common.rest.swagger;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.METHOD, ElementType.TYPE, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@ApiResponse(description = SwaggerConstants.NO_CONTENT_DESCRIPTION, responseCode = SwaggerConstants.NO_CONTENT_CODE)
@ApiResponse(description = SwaggerConstants.BAD_REQUEST_DESCRIPTION, responseCode = SwaggerConstants.BAD_REQUEST_CODE, content = { @Content })
@ApiResponse(description = SwaggerConstants.SERVER_ERROR_DESCRIPTION, responseCode = SwaggerConstants.SERVER_ERROR_CODE, content = { @Content })
public @interface NoContentRes {
}
