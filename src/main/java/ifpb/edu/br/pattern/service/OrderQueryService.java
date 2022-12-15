package ifpb.edu.br.pattern.service;

import java.util.List;

import ifpb.edu.br.pattern.dto.PurchaseOrderSummaryDto;

public interface OrderQueryService {
    List<PurchaseOrderSummaryDto> getSaleSummaryGroupByState();

    PurchaseOrderSummaryDto getSaleSummaryByState(String state);

    double getTotalSale();
}
