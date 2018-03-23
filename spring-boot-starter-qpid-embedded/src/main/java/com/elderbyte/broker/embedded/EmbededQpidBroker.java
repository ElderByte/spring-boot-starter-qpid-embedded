package com.elderbyte.broker.embedded;

import org.apache.qpid.server.SystemLauncher;
import org.apache.qpid.server.model.SystemConfig;
import org.apache.qpid.server.model.port.HttpPort;
import org.apache.qpid.server.model.port.HttpPortImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;


public class EmbededQpidBroker {

    /***************************************************************************
     *                                                                         *
     * Fields                                                                  *
     *                                                                         *
     **************************************************************************/

    private final Logger log = LoggerFactory.getLogger(getClass());
    private final SystemLauncher brokerLauncher;

    private String vhost;
    private int amqpPort;
    private int managementPort;

    private String configJsonUrl = null;

    /***************************************************************************
     *                                                                         *
     * Constructor                                                             *
     *                                                                         *
     **************************************************************************/

    public EmbededQpidBroker(
            int amqpPort,
            String vhost,
            int managementPort,
            String configJsonUrl){



        if(configJsonUrl != null && configJsonUrl.isEmpty()) throw new IllegalArgumentException("configJsonUrl must be a valid path: '" + configJsonUrl + "'");

        this.amqpPort = amqpPort;
        this.managementPort = managementPort;
        this.vhost = vhost;
        this.configJsonUrl = configJsonUrl;

        brokerLauncher = new SystemLauncher();
    }

    /***************************************************************************
     *                                                                         *
     * Public API                                                              *
     *                                                                         *
     **************************************************************************/

    public void start(){
        System.out.println("Starting QPID Broker @ " + vhost + " - amqp @ " + amqpPort + "... ");

        try{
            brokerLauncher.startup(createSystemConfig());
        }catch (Exception e){
            throw new RuntimeException("Failed to start embedded QPID broker!", e);
        }
    }

    public void shutdown(){
        log.info("Shutting down broker...");
        brokerLauncher.shutdown();
    }

    /***************************************************************************
     *                                                                         *
     * Private methods                                                         *
     *                                                                         *
     **************************************************************************/

    private Map<String, Object> createSystemConfig(){

        System.setProperty("qpid.amqp_port", amqpPort + "");
        System.setProperty("qpid.http_port", managementPort + "");
        System.setProperty("qpid.vhost", vhost);

        Map<String, Object> attributes = new HashMap<>();
        attributes.put(SystemConfig.TYPE, "Memory");
        if(configJsonUrl != null && !configJsonUrl.isEmpty()){
            attributes.put(SystemConfig.INITIAL_CONFIGURATION_LOCATION, configJsonUrl);
        }
        attributes.put(SystemConfig.STARTUP_LOGGED_TO_SYSTEM_OUT, false);

        return attributes;
    }
}

