package com.alok.home.gateway.filters;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class GlobalCustomFilterGatewayFilterFactory extends AbstractGatewayFilterFactory<GlobalCustomFilterGatewayFilterFactory.Config> {

    public GlobalCustomFilterGatewayFilterFactory() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            // Pre-filter logic
            System.out.println("GlobalCustomFilterGatewayFilterFactory pre-filter executed for request: " + exchange.getRequest().getURI());

            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                // Post-filter logic
                System.out.println("GlobalCustomFilterGatewayFilterFactory post-filter executed for request: " + exchange.getRequest().getURI());
            }));
        };
    }

    public static class Config {
        // Configuration properties can be added here if needed
    }
}
