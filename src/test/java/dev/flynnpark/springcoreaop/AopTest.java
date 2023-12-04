package dev.flynnpark.springcoreaop;

import dev.flynnpark.springcoreaop.order.OrderRepository;
import dev.flynnpark.springcoreaop.order.OrderService;
import dev.flynnpark.springcoreaop.order.aop.AspectV3;
import dev.flynnpark.springcoreaop.order.aop.AspectV4Pointcut;
import dev.flynnpark.springcoreaop.order.aop.AspectV5Order;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Slf4j
@SpringBootTest
@Import({AspectV5Order.LogAspect.class, AspectV5Order.TransactionAspect.class})
public class AopTest {
    @Autowired
    OrderService orderService;

    @Autowired
    OrderRepository orderRepository;

    @Test
    void aopInfo() {
        log.info("isAopProxy, orderService: {}", AopUtils.isAopProxy(orderService));
        log.info("isAopProxy, orderRepository: {}", AopUtils.isAopProxy(orderRepository));
    }

    @Test
    void success() {
        orderService.order("ok");
    }

    @Test
    void exception() {
        Assertions.assertThatThrownBy(() -> orderService.order("ex"))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
