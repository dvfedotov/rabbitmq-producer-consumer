package ru.dfed.rabbitmqproducerconsumer.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import ru.dfed.rabbitmqproducerconsumer.config.MqConfig;
import ru.dfed.rabbitmqproducerconsumer.domain.LoggingOrder;

@Slf4j
@Service
public class OrderService {


    private final RabbitTemplate template;


    public OrderService(RabbitTemplate template) {
        this.template = template;
    }

    public void sendLoggingOrder(LoggingOrder order) {
        log.info("Push {}", order.toString());
        template.convertAndSend(MqConfig.MESSAGE_EXCHANGE_2, order.getStatus(), order);
    }
}
