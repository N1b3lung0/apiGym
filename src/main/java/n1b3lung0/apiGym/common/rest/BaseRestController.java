package n1b3lung0.apiGym.common.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.UUID;

public abstract class BaseRestController {

    protected static final String ID_PATH = "/{id}";

    protected final ResponseEntity<Void> createdResponse(UUID id) {
        return ResponseEntity.created(
                ServletUriComponentsBuilder
                        .fromCurrentRequest().path(ID_PATH)
                        .buildAndExpand(id).toUri())
                .build();
    }
}
