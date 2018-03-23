package com.elderbyte.broker.embedded;

import com.elderbyte.broker.EnableEmbeddedBroker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

import java.net.UnknownHostException;


@EnableEmbeddedBroker
@SpringBootApplication
public class QpidServer {
    public static void main(String[] args) throws UnknownHostException {
        SpringApplication app = new SpringApplication(QpidServer.class);
        Environment env = app.run(args).getEnvironment();
    }
}
