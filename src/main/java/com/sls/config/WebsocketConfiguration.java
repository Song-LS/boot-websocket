package com.sls.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * @author sls
 **/
@Configuration
public class WebsocketConfiguration {

    public ServerEndpointExporter serverEndpointEcporter() {
        return new ServerEndpointExporter();
    }
}
