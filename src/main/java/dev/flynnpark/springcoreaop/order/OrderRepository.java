package dev.flynnpark.springcoreaop.order;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class OrderRepository {
    public String save(String itemId) {
        log.info("OrderRepository.save({})", itemId);
        if (itemId.equals("ex")) {
            throw new IllegalArgumentException("Invalid ItemId");
        }
        return "ok";
    }
}
