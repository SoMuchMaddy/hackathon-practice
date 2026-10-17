package huskyfinance;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.math.BigDecimal;

@Repository
public class TransactionRepository {

    private final JdbcTemplate jdbcTemplate;

    public TransactionRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void save(Transaction transaction) {
        String sql = """
        INSERT INTO transactions (description, amount, type)
        VALUES (?, ?, ?)
        """;

        jdbcTemplate.update(
                sql,
                transaction.getDescription(),
                transaction.getAmount().toPlainString(),
                transaction.getType()
        );
    }

    public List<Transaction> findAll() {
        String sql = "SELECT * FROM transactions ORDER BY id DESC";

        return jdbcTemplate.query(sql, (rs, rowNum) ->
                new Transaction(
                        rs.getInt("id"),
                        rs.getString("description"),
                        new BigDecimal(rs.getString("amount")),
                        rs.getString("type")
                )
        );
    }
}