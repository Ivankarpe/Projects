package com.praktukym.gameServer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.handler.WebSocketHandlerDecorator;

import com.praktukym.gameServer.WebSocketControllers.WebSocketLikeStompHandler;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    private final WebSocketLikeStompHandler webSocketLikeStompHandler;

    @Autowired
    public WebSocketConfig(WebSocketLikeStompHandler webSocketLikeStompHandler) {
        this.webSocketLikeStompHandler = webSocketLikeStompHandler;
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(new WebSocketHandlerDecorator(webSocketLikeStompHandler), "/ws")
                .setAllowedOrigins("*");
    }
}
