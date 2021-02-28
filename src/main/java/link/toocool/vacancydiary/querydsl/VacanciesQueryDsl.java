package link.toocool.vacancydiary.querydsl;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import link.toocool.vacancydiary.entity.QVacancy;
import link.toocool.vacancydiary.entity.Vacancy;
import link.toocool.vacancydiary.querydsl.bean.VacanciesQueryBean;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import static java.util.Objects.nonNull;

@Repository
@RequiredArgsConstructor
public class VacanciesQueryDsl extends QueryDslJpa<Vacancy, VacanciesQueryBean> {

    protected final JPAQueryFactory queryFactory;

    private QVacancy vacancy = QVacancy.vacancy;

    @Override
    protected JPAQuery<Vacancy> getQuery(VacanciesQueryBean parametersBean) {
        JPAQuery<Vacancy> query = queryFactory.selectFrom(vacancy);

        applyFilterStatus(query, parametersBean);
        applyFilterCompanyName(query, parametersBean);

        if (Sort.Direction.DESC.equals(parametersBean.getDirection())) {
            query.orderBy(vacancy.createdDate.desc());
        } else {
            query.orderBy(vacancy.createdDate.asc());
        }

        return query;
    }

    private void applyFilterStatus(JPAQuery<Vacancy> query, VacanciesQueryBean parametersBean) {
        if (nonNull(parametersBean.getStatus())) {
            query.where(vacancy.status.eq(parametersBean.getStatus()));
        }
    }

    private void applyFilterCompanyName(JPAQuery<Vacancy> query, VacanciesQueryBean parametersBean) {
        if (nonNull(parametersBean.getCompanyName())) {
            query.where(vacancy.companyName.eq(parametersBean.getCompanyName()));
        }
    }
}
