package com.elderbyte.broker.config;

import com.elderbyte.broker.embedded.EmbededQpidBroker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.net.URL;

/**
 * Configures and starts an Qpid Message Broker.
 */
@Configuration
@ConditionalOnProperty(value = "qpid.embedded.enabled", matchIfMissing = true)
public class EmbeddedQpidBrokerConfiguration {

    /***************************************************************************
     *                                                                         *
     * Fields                                                                  *
     *                                                                         *
     **************************************************************************/

    private final Logger log = LoggerFactory.getLogger(getClass());

    private static final String INITIAL_CONFIGURATION = "initial-config.json";

    @Value("${qpid.embedded.port:5672}")
    private int amqpPort;

    @Value("${qpid.embedded.management.port:8085}")
    private int httpPort;

    @Value(("${qpid.embedded.vhost:default}"))
    private String virtualHost;

    @Value(("${qpid.embedded.configurationUrl:}"))
    private String configurationUrl;

    @Value(("${qpid.embedded.user.name:guest}"))
    private String username;

    @Value(("${qpid.embedded.user.password:guest}"))
    private String password;

    private EmbededQpidBroker broker;

    /***************************************************************************
     *                                                                         *
     * Constructor                                                             *
     *                                                                         *
     **************************************************************************/

    public EmbeddedQpidBrokerConfiguration() {  }

    /***************************************************************************
     *                                                                         *
     * Event hooks                                                             *
     *                                                                         *
     **************************************************************************/

    @PostConstruct
    public void startBroker(){

        if(configurationUrl == null || configurationUrl.isEmpty()){
            URL initialConfig = EmbeddedQpidBrokerConfiguration.class.getClassLoader().getResource(INITIAL_CONFIGURATION);
            configurationUrl = initialConfig.toString();
        }

        broker = new EmbededQpidBroker(
                amqpPort,
                virtualHost,
                httpPort,
                username,
                password,
                configurationUrl
        );

        broker.start();
    }

    @PreDestroy
    public void shutdown(){
        broker.shutdown();
    }


}
