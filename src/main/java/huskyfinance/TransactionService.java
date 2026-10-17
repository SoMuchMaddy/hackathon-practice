package huskyfinance;

import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.List;

@Service
public class TransactionService {

    private final TransactionRepository repository;

    public TransactionService(TransactionRepository repository) {
        this.repository = repository;
    }

    public BigDecimal getTotalIncome() {
        BigDecimal total = BigDecimal.ZERO;

        List<Transaction> transactions = repository.findAll();

        for (Transaction transaction : transactions) {
            if ("INCOME".equals(transaction.getType())) {
                total = total.add(transaction.getAmount());
            }
        }

        return total;
    }

    public BigDecimal getTotalExpenses() {
        BigDecimal total = BigDecimal.ZERO;

        List<Transaction> transactions = repository.findAll();

        for (Transaction transaction : transactions) {
            if ("EXPENSE".equals(transaction.getType())) {
                total = total.add(transaction.getAmount());
            }
        }

        return total;
    }

    public BigDecimal getBalance() {
        return getTotalIncome().subtract(getTotalExpenses());
    }

    public void validateTransaction(Transaction transaction) {
        if (transaction.getDescription() == null ||
                transaction.getDescription().isBlank()) {
            throw new IllegalArgumentException(
                    "Description cannot be empty.");
        }

        if (transaction.getAmount() == null ||
                transaction.getAmount().signum() <= 0) {
            throw new IllegalArgumentException(
                    "Amount must be greater than zero.");
        }

        if (!"INCOME".equals(transaction.getType()) &&
                !"EXPENSE".equals(transaction.getType())) {
            throw new IllegalArgumentException(
                    "Type must be INCOME or EXPENSE.");
        }
    }
}