package com.elderbyte.broker.autoconfigure;

import com.elderbyte.broker.config.EmbeddedQpidBrokerConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import( { EmbeddedQpidBrokerConfiguration.class } )
public class QpidBrokerClientAutoConfiguration {

}
