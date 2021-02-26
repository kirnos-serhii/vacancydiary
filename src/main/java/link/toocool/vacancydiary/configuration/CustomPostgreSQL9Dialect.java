package link.toocool.vacancydiary.configuration;

import org.hibernate.dialect.PostgreSQL94Dialect;
import org.hibernate.dialect.function.StandardSQLFunction;
import org.hibernate.type.StringType;

public class CustomPostgreSQL9Dialect extends PostgreSQL94Dialect {

    public CustomPostgreSQL9Dialect() {
        super();
        registerFunction("string_agg", new StandardSQLFunction("string_agg", new StringType()));
    }
}
