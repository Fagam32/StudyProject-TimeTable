package config;

import service.RabbitMQService;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

@Startup
@Singleton
public class StartupConfiguration {

    RabbitMQService rabbitMQService = new RabbitMQService();

    @PostConstruct
    private void initRabbitMQ() throws IOException, TimeoutException {
        rabbitMQService.init();
    }
}
