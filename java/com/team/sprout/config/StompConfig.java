package com.team.sprout.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketTransportRegistration;

import com.team.sprout.interceptor.HandshakeInterceptor;



@Configuration
@EnableWebSocketMessageBroker
public class StompConfig extends AbstractWebSocketMessageBrokerConfigurer {

	@Autowired
	private HandshakeInterceptor handshakeInterceptor;
	
	@Override
	public void configureWebSocketTransport(WebSocketTransportRegistration registration) {
		registration.setSendTimeLimit(15 * 1000).setSendBufferSizeLimit(512 * 1024);
		super.configureWebSocketTransport(registration);
	}

	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry) {
		
		long heartbeatServer = 10000; // 10 seconds
        long heartbeatClient = 10000; // 10 seconds

        ThreadPoolTaskScheduler ts = new ThreadPoolTaskScheduler();
        ts.setPoolSize(2);
        ts.setThreadNamePrefix("wss-heartbeat-thread-");
        ts.initialize();

        registry.enableSimpleBroker("/subscribe")
                .setHeartbeatValue(new long[]{heartbeatServer, heartbeatClient})
                .setTaskScheduler(ts);
		
	}

	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		registry.addEndpoint("/endpoint").setAllowedOrigins("*").withSockJS().setInterceptors(handshakeInterceptor);
	}

}
