package com.yasho.restutils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.undertow.server.HttpServerExchange;

public class ResponseSender {

    private final ObjectMapper objectMapper;

    public ResponseSender() {
        this.objectMapper = new ObjectMapper();
    }


    public void send(HttpServerExchange exchange, Object responseObject) {
        String responseString = null;
        try {
            responseString = objectMapper.writeValueAsString(responseObject);
        } catch (JsonProcessingException e) {
            try {
                responseString = objectMapper.writeValueAsString(new ErrorResponseEntity(500, "Internal Server Error"));
            } catch (JsonProcessingException ex) {
                responseString = "";
            }
        }
        exchange.getResponseSender().send(responseString);

    }

    public void sendError(HttpServerExchange exchange, Exception exception) {
        String responseString = null;
        try {
            responseString = objectMapper.writeValueAsString(new ErrorResponseEntity(500, "Internal Server Error : " + exception.getMessage()));
        } catch (JsonProcessingException ex) {
            responseString = "";
        }
        exchange.getResponseSender().send(responseString);
    }
}
