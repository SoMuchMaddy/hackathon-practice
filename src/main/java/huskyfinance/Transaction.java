package huskyfinance;
import java.math.BigDecimal;

public class Transaction {

    private Integer id;
    private String description;
    private BigDecimal amount;
    private String type;

    public Transaction() {

    }

    public Transaction(Integer id, String description, BigDecimal amount, String type) {
        this.id = id;
        this.description = description;
        this.amount = amount;
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getType() {
        return type;
    }

    public void setID(Integer id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public void setType(String type) {
        this.type = type;
    }
}
