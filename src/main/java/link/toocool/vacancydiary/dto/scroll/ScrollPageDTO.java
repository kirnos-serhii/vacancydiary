package link.toocool.vacancydiary.dto.scroll;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ScrollPageDTO<T> {

    private List<T> listObjects;

    private Integer totalPages;

    private Integer currentPage;

    private Long totalElements;
}
