package n1b3lung0.apiGym.common.rest.swagger;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.METHOD, ElementType.TYPE, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@ApiResponse(description = SwaggerConstants.NOT_FOUND_DESCRIPTION, responseCode = SwaggerConstants.NOT_FOUND_CODE, content = { @Content })
public @interface NotFoundRes {
}
