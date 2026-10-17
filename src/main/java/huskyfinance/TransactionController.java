package huskyfinance;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.Map;
import java.math.BigDecimal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    private final TransactionRepository repository;

    private final TransactionService service;

    public TransactionController(TransactionRepository repository,
                                 TransactionService service) {
        this.repository = repository;
        this.service = service;
    }

    @GetMapping
    public List<Transaction> getTransactions() {
        return repository.findAll();
    }

    @PostMapping
    public void addTransaction(@RequestBody Transaction transaction) {
        repository.save(transaction);
    }

    @GetMapping("/summary")
    public Map<String, BigDecimal> getSummary() {
        return Map.of(
                "income", service.getTotalIncome(),
                "expenses", service.getTotalExpenses(),
                "balance", service.getBalance()
        );
    }

    @DeleteMapping("/{id}")
    public void deleteTransaction(@PathVariable int id) {
        repository.deleteById(id);
    }
}