package com.elderbyte.broker;

import com.elderbyte.broker.config.EmbeddedQpidBrokerConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;


@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import( { EmbeddedQpidBrokerConfiguration.class } )
public @interface EnableEmbeddedBroker {

}
