package ru.dfed.rabbitmqproducerconsumer.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import ru.dfed.rabbitmqproducerconsumer.config.MqConfig;
import ru.dfed.rabbitmqproducerconsumer.domain.CustomMessage;
import ru.dfed.rabbitmqproducerconsumer.domain.LoggingOrder;

@Slf4j
@Component
public class MessageListener {

    private final OrderService orderService;

    public MessageListener(OrderService orderService) {
        this.orderService = orderService;
    }


    @RabbitListener(queues = MqConfig.MESSAGE_QUEUE_1)
    public void listener1(CustomMessage message) {

        log.info("Received liquid from {}", message.toString());
        if (message.getNumber() > 50) {
            log.error("Total liquid number must be < 10, but: {}", message.getNumber());
            orderService.sendLoggingOrder(new LoggingOrder("error", message));
        } else if (message.getAmount() > 500) {
            log.error("Total amount must be < 500, but: {}", message.getAmount());
            orderService.sendLoggingOrder(new LoggingOrder("error", message));
        } else {
            log.info("An order for the amount of: {}", message.getAmount());
            orderService.sendLoggingOrder(new LoggingOrder("info", message));
        }


    }

    @RabbitListener(queues = MqConfig.MESSAGE_QUEUE_2)
    public void listener2(CustomMessage message) {
        log.info("Received countable from {}", message.toString());
        if (message.getNumber() > 50) {
            log.error("Total countable number must be < 100, but: {}", message.getNumber());
            orderService.sendLoggingOrder(new LoggingOrder("error", message));
        } else if (message.getAmount() > 500) {
            log.error("Total amount must be < 500, but: {}", message.getAmount());
            orderService.sendLoggingOrder(new LoggingOrder("error", message));
        } else {
            log.info("An order for the amount of: {}", message.getAmount());
            orderService.sendLoggingOrder(new LoggingOrder("info", message));
        }
    }

}
