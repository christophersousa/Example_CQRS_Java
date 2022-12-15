package ifpb.edu.br.pattern.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ifpb.edu.br.pattern.model.PurchaseOrderSummary;

@Repository
public interface PurchaseOrderSummaryRepository extends JpaRepository<PurchaseOrderSummary, Long> {

    Optional<PurchaseOrderSummary> findByState(String state);
}
