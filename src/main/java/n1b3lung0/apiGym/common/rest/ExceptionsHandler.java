package n1b3lung0.apiGym.common.rest;

import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import n1b3lung0.apiGym.common.application.utils.exception.ExceptionConstants;
import n1b3lung0.apiGym.common.domain.exception.AlreadyExists;
import n1b3lung0.apiGym.common.domain.exception.Forbidden;
import n1b3lung0.apiGym.common.domain.exception.NotAllowed;
import n1b3lung0.apiGym.common.domain.exception.NotFound;
import n1b3lung0.apiGym.common.domain.exception.NotValid;
import n1b3lung0.apiGym.common.rest.dto.ApiError;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
@ControllerAdvice
public class ExceptionsHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({NotFound.class})
    public ResponseEntity<Object> handleNotFound(NotFound ex, WebRequest req) {
        ApiError apiError = new ApiError(
                HttpStatus.NOT_FOUND,
                ExceptionConstants.NOT_FOUND,
                ex.getMessage());
        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    }

    @ExceptionHandler({NotValid.class})
    public ResponseEntity<Object> handleNotValid(NotValid ex, WebRequest req) {
        ApiError apiError = new ApiError(
                HttpStatus.BAD_REQUEST,
                ExceptionConstants.NOT_VALID,
                ex.getErrors());
        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    }

    @ExceptionHandler({AlreadyExists.class})
    public ResponseEntity<Object> handleAlreadyExists(AlreadyExists ex, WebRequest req) {
        ApiError apiError = new ApiError(
                HttpStatus.BAD_REQUEST,
                ExceptionConstants.ALREADY_EXISTS,
                ex.getMessage());
        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    }

    @ExceptionHandler({NotAllowed.class})
    public ResponseEntity<Object> handleNotAllowed(NotAllowed ex, WebRequest req) {
        ApiError apiError = new ApiError(
                HttpStatus.CONFLICT,
                ExceptionConstants.NOT_ALLOWED,
                ex.getMessage());
        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    }

    @ExceptionHandler({Forbidden.class})
    public ResponseEntity<Object> handleForbidden(Forbidden ex, WebRequest req) {
        ApiError apiError = new ApiError(
                HttpStatus.FORBIDDEN,
                ExceptionConstants.FORBIDDEN,
                ex.getMessage());
        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    }

    @ExceptionHandler({IllegalArgumentException.class})
    public ResponseEntity<Object> handleIllegalArgument(IllegalArgumentException ex, WebRequest req) {
        log.error(ExceptionConstants.ILLEGAL_ARGUMENT + ": " + ExceptionUtils.getStackTrace(ex));
        ApiError apiError = new ApiError(
                HttpStatus.BAD_REQUEST,
                ExceptionConstants.ILLEGAL_ARGUMENT,
                ex.getLocalizedMessage());
        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    }

    @ExceptionHandler({DataIntegrityViolationException.class})
    public ResponseEntity<Object> handleDataIntegrityViolation(DataIntegrityViolationException ex, WebRequest req) {
        log.error(ExceptionConstants.DATA_INTEGRITY_VIOLATION + ": " + ExceptionUtils.getStackTrace(ex));
        ApiError apiError = new ApiError(
                HttpStatus.BAD_REQUEST,
                ExceptionConstants.DATA_INTEGRITY_VIOLATION,
                ex.getLocalizedMessage());
        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    }

    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException ex, WebRequest req) {
        List<String> errors = new ArrayList<>();
        ex.getConstraintViolations().stream()
                .map(violation -> violation.getRootBeanClass().getName() + " " + violation.getPropertyPath() + ": " + violation.getMessage())
                .forEach(errors::add);
        ApiError apiError = new ApiError(
                HttpStatus.BAD_REQUEST,
                ExceptionConstants.CONSTRAINT_VIOLATION,
                errors);
        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        List<String> errors = new ArrayList<>();
        ex.getBindingResult().getFieldErrors().stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .forEach(errors::add);
        ex.getGlobalErrors().stream()
                .map(error -> error.getObjectName() + ": " + error.getDefaultMessage())
                .forEach(errors::add);

        ApiError apiError = new ApiError(
                HttpStatus.BAD_REQUEST,
                ExceptionConstants.ARGUMENT_NOT_VALID,
                errors);
        return handleExceptionInternal(ex, apiError, headers, apiError.getStatus(), request);
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        String error = ex.getParameterName() + " parameter is missing";
        ApiError apiError = new ApiError(
                HttpStatus.BAD_REQUEST,
                ex.getLocalizedMessage(),
                error);
        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    }

    @ExceptionHandler({MethodArgumentTypeMismatchException.class})
    public ResponseEntity<Object> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex, WebRequest request) {
        String error = ex.getName() + " should be of type " + Objects.requireNonNull(ex.getRequiredType()).getName();
        ApiError apiError = new ApiError(
                HttpStatus.BAD_REQUEST,
                ex.getLocalizedMessage(),
                error);
        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        String error = "No handler found for " + ex.getHttpMethod() + " " + ex.getRequestURL();
        ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, ex.getLocalizedMessage(), error);
        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    }

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        StringBuilder builder = new StringBuilder();
        builder.append(ex.getMethod());
        builder.append(" method is not supported for this request. Supported methods are ");
        Objects.requireNonNull(ex.getSupportedHttpMethods()).forEach(t -> builder.append(t).append(" "));
        ApiError apiError = new ApiError(
                HttpStatus.METHOD_NOT_ALLOWED,
                ex.getLocalizedMessage(),
                builder.toString());
        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    }

    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        StringBuilder builder = new StringBuilder();
        builder.append(ex.getContentType());
        builder.append(" media type is not supported. Supported media types are ");
        ex.getSupportedMediaTypes().forEach(t -> builder.append(t + ", "));
        ApiError apiError = new ApiError(
                HttpStatus.UNSUPPORTED_MEDIA_TYPE,
                ex.getLocalizedMessage(),
                builder.substring(0, builder.length() - 2));
        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<Object> handleAll(Exception ex, WebRequest request) {
        log.error("Internal Server Error: " + ExceptionUtils.getStackTrace(ex));
        ApiError apiError = new ApiError(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "Error occurred",
                ex.getLocalizedMessage());
        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    }
}
