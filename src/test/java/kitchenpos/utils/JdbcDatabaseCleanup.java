package kitchenpos.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.util.List;

@Primary
@Repository
@ActiveProfiles("test")
public class JdbcDatabaseCleanup implements DatabaseCleanup{
    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    private List<String> tableNames;

    public JdbcDatabaseCleanup(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        readAllTableNames();
    }

    @Transactional
    public void execute() {
        jdbcTemplate.execute("SET REFERENTIAL_INTEGRITY FALSE", PreparedStatement::executeUpdate);
        for (String tableName : tableNames) {
            jdbcTemplate.execute("TRUNCATE TABLE " + tableName, PreparedStatement::executeUpdate);
            initSequence(tableName);
        }
        jdbcTemplate.execute("SET REFERENTIAL_INTEGRITY TRUE", PreparedStatement::executeUpdate);
    }

    private void readAllTableNames() {
        tableNames = jdbcTemplate.query("SHOW TABLES", (resultSet,rownum)-> resultSet.getString("TABLE_NAME").toLowerCase());
        excludeNonInitializingTable();
    }

    private void excludeNonInitializingTable() {
        tableNames.remove("flyway_schema_history");
    }

    private void initSequence(String tableName) {
        try {
            jdbcTemplate.execute("ALTER TABLE " + tableName + " ALTER COLUMN ID RESTART WITH 1", PreparedStatement::executeUpdate);
        } catch (BadSqlGrammarException e) {
            jdbcTemplate.execute("ALTER TABLE " + tableName + " ALTER COLUMN SEQ RESTART WITH 1", PreparedStatement::executeUpdate);
        }
    }
}