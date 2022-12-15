package ifpb.edu.br.pattern.service.impl;

import java.time.Duration;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ifpb.edu.br.pattern.model.Product;
import ifpb.edu.br.pattern.model.PurchaseOrder;
import ifpb.edu.br.pattern.model.PurchaseOrderSummary;
import ifpb.edu.br.pattern.model.User;
import ifpb.edu.br.pattern.repository.ProductRepository;
import ifpb.edu.br.pattern.repository.PurchaseOrderRepository;
import ifpb.edu.br.pattern.repository.PurchaseOrderSummaryRepository;
import ifpb.edu.br.pattern.repository.UserRepository;
import ifpb.edu.br.pattern.service.OrderCommandService;
import jakarta.annotation.PostConstruct;

@Service
public class OrderCommandServiceImpl implements OrderCommandService {

    private static final long ORDER_CANCELLATION_WINDOW = 30;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private PurchaseOrderRepository purchaseOrderRepository;

    @Autowired
    private PurchaseOrderSummaryRepository purchaseOrderSummaryRepository;

    private List<User> users;
    private List<Product> products;

    @PostConstruct
    private void init() {
        this.users = this.userRepository.findAll();
        this.products = this.productRepository.findAll();
    }

    @Override
    public void createOrder(int userIndex, int productIndex) {
        PurchaseOrder purchaseOrder = new PurchaseOrder();
        purchaseOrder.setProductId(this.products.get(productIndex).getId());
        purchaseOrder.setUserId(this.users.get(userIndex).getId());
        purchaseOrder.setOrderDate(new Date(System.currentTimeMillis()));
        this.purchaseOrderRepository.save(purchaseOrder);

        PurchaseOrderSummary purchaseOrderSummary = purchaseOrderSummaryRepository
                .findByState(this.users.get(userIndex).getState())
                .orElse(new PurchaseOrderSummary());
        purchaseOrderSummary.setState(this.users.get(userIndex).getState());

        if (purchaseOrderSummary.getTotalSale() == null) {
            purchaseOrderSummary.setTotalSale(0.0);
        }

        purchaseOrderSummary
                .setTotalSale(purchaseOrderSummary.getTotalSale() + this.products.get(productIndex).getPrice());

        purchaseOrderSummaryRepository.save(purchaseOrderSummary);
    }

    @Override
    public void cancelOrder(long orderId) {
        this.purchaseOrderRepository.findById(orderId)
                .ifPresent(purchaseOrder -> {
                    this.purchaseOrderRepository.deleteById(orderId);

                    User user = this.users.stream()
                            .filter(u -> u.getId().equals(purchaseOrder.getUserId()))
                            .findAny()
                            .orElse(null);

                    purchaseOrderSummaryRepository
                            .findByState(user.getState())
                            .ifPresent(p -> {
                                System.out.println("Usuario:" + p.toString());
                                Product product = (Product) this.products.stream()
                                        .filter(pr -> pr.getId() == purchaseOrder.getProductId())
                                        .findAny()
                                        .orElse(null);

                                p.setTotalSale(p.getTotalSale() - product.getPrice());
                                purchaseOrderSummaryRepository.save(p);
                            });

                });
    }
}
