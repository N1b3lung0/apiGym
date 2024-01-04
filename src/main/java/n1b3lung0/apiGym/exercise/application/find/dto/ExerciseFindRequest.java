package n1b3lung0.apiGym.exercise.application.find.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import n1b3lung0.apiGym.common.domain.criteria.Pagination;
import n1b3lung0.apiGym.common.domain.criteria.SortDirection;
import n1b3lung0.apiGym.common.domain.criteria.Sorting;
import n1b3lung0.apiGym.exercise.domain.ExerciseSearchCriteria;
import org.apache.commons.lang3.StringUtils;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Solicitud de búsqueda de ejercicios")
public class ExerciseFindRequest {

    @Schema(description = "Término de búsqueda (en nombre y descripción)")
    private String query;

    @Schema(description = "Página de resultados (vacío desactiva paginación)")
    private Integer page;

    @Schema(description = "Número de resultados por página (vacío desactiva paginación)")
    private Integer size;

    @Schema(description = "Campo de referencia para ordenado. Por defecto, fecha de creación")
    private String sortBy;

    @Schema(description = "Dirección del ordenado (ASC, DESC). Por defecto, DESC")
    private SortDirection sortDirection;

    public ExerciseSearchCriteria toCriteria() {
        Pagination pagination = page != null && size != null
                ? Pagination.of(page, size)
                : Pagination.unpaged();
        Sorting sorting = StringUtils.isNotBlank(sortBy) && sortDirection != null
                ? Sorting.of(sortBy, sortDirection)
                : Sorting.of("auditFields.createdAt", SortDirection.DESC);
        return new ExerciseSearchCriteria(pagination, sorting, query);
    }
}
