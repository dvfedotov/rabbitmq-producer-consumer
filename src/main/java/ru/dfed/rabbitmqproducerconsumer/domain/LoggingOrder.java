package ru.dfed.rabbitmqproducerconsumer.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class LoggingOrder {

    private String status;
    private CustomMessage message;

}
