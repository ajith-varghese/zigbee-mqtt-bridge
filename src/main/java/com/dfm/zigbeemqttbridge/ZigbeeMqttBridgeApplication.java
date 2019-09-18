package com.dfm.zigbeemqttbridge;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.integration.config.EnableIntegration;


@SpringBootApplication
@EnableIntegration
public class ZigbeeMqttBridgeApplication {

	@Autowired
	ApplicationContext context;
	
	@Autowired
	WSClient wsClient;
	
	public static void main(String[] args) {
        new SpringApplicationBuilder(ZigbeeMqttBridgeApplication.class)
        			.web(WebApplicationType.NONE)
                .run(args);
    }
   
    
}



 