package dev.flynnpark.springcoreaop.order;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public String order(String itemId) {
        log.info("OrderService.save({})", itemId);
        return orderRepository.save(itemId);
    }
}
