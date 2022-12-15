package ifpb.edu.br.pattern.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor()
@NoArgsConstructor
public class PurchaseOrderSummary {
    @Id
    private String state;
    private Double totalSale;
}
