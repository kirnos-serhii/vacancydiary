package link.toocool.vacancydiary.querydsl.bean;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.logging.log4j.util.Strings;
import org.springframework.data.domain.Sort.Direction;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class QueryBasicBean {

    public String orderBy = Strings.EMPTY;

    public Direction direction = Direction.ASC;

    public Integer page = 1;
}
