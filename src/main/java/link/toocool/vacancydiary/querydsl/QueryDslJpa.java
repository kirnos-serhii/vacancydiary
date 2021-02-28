package link.toocool.vacancydiary.querydsl;

import com.querydsl.jpa.impl.JPAQuery;
import link.toocool.vacancydiary.querydsl.bean.QueryBasicBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static link.toocool.vacancydiary.util.Constant.PAGE_SIZE;

public abstract class QueryDslJpa<T, P extends QueryBasicBean> {

    protected abstract JPAQuery<T> getQuery(P parametersBean);

    public Page<T> executePagebale(P parametersBean) {
        JPAQuery<T> query = getQuery(parametersBean);

        Pageable pageable = PageRequest.of(parametersBean.getPage() - 1, PAGE_SIZE);

        List<T> resultList = query
            .limit(pageable.getPageSize())
            .offset(pageable.getOffset())
            .fetch();

        long totalCount = query.fetchCount();

        return new PageImpl<>(resultList, pageable, totalCount);
    }

    public List<T> executeList(P parametersBean) {
        return getQuery(parametersBean).fetch();
    }

    public T executeSingle(P parametersBean) {
        return getQuery(parametersBean).fetchOne();
    }
}
