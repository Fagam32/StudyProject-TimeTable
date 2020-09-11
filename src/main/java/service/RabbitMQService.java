package service;

import com.rabbitmq.client.*;
import websocket.StationWebSocketEndpoint;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;


public class RabbitMQService {
    private static final String QUEUE_NAME = "stationUpdates";

    public void init() throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("rabbitmq");
        factory.setPort(5672);
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare(QUEUE_NAME, true, false, false, null);

        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) {
                String message = new String(body, StandardCharsets.UTF_8);
                String[] stations = message.split("/");
                for (String station : stations)
                    StationWebSocketEndpoint.send(station, "update");
            }
        };
        channel.basicConsume(QUEUE_NAME, true, consumer);
    }
}
