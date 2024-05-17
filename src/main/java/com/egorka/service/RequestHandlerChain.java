// RequestHandlerChain.java
package com.egorka.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
public class RequestHandlerChain {
    private List<RequestHandler> handlers;

    @Autowired
    public RequestHandlerChain(List<RequestHandler> handlers) {
        this.handlers = handlers;
    }

    public void handleRequest(int choice, ControlPanelProxy proxy, Scanner scanner) {
        for (RequestHandler handler : handlers) {
            handler.handleRequest(choice, proxy, scanner);
        }
    }

    public List<RequestHandler> getHandlers() {
        return handlers;
    }

    public void setHandlers(List handlers) {
        this.handlers = handlers;
    }
}
