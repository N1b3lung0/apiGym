package n1b3lung0.apiGym.common.application.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import n1b3lung0.apiGym.common.domain.criteria.Page;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Respuesta paginada a una solicitud de búsqueda")
public class PageResponse<T> {

    @Schema(description = "Número de elementos")
    private List<T> items;

    @Schema(description = "Número de página")
    private int pageNumber;

    @Schema(description = "Elementos por página")
    private int pageSize;

    @Schema(description = "Booleano que indica si es la primera página")
    private boolean first;

    @Schema(description = "Booleano que indica si es la última página")
    private boolean last;

    public PageResponse(Page<T> page) {
        this.items = page.getContent();
        this.pageNumber = page.getNumber();
        this.pageSize = page.getSize();
        this.first = page.isFirst();
        this.last = page.isLast();
    }

    public PageResponse(Page<?> page, List<T> items) {
        this.items = items;
        this.pageNumber = page.getNumber();
        this.pageSize = page.getSize();
        this.first = page.isFirst();
        this.last = page.isLast();
    }
}
