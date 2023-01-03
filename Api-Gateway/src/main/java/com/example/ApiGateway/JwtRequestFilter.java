package com.example.ApiGateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import io.jsonwebtoken.Claims;
import reactor.core.publisher.Mono;

@Component
@Order(-2)
public class JwtRequestFilter implements GatewayFilter {

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private RouterValidator routerValidator;

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		System.out.println("*******************Call In Filter*******************");
		ServerHttpRequest request = exchange.getRequest();
		if (routerValidator.isSecured.test(request)) {

			if (isAuthMissing(request))
				return onError(exchange, "Authorization header is missing in request", HttpStatus.UNAUTHORIZED);

			final String token = getAuthHeader(request).substring(7);
			if (jwtUtil.isTokenExpired(token)) {
				return onError(exchange, "Authorization header is invalid", HttpStatus.UNAUTHORIZED);
			}

			this.populateRequestWithHeaders(exchange, token);
		}
		return chain.filter(exchange);
	}

	private Mono<Void> onError(ServerWebExchange exchange, String err, HttpStatus httpStatus) {
		ServerHttpResponse response = exchange.getResponse();
		response.setStatusCode(httpStatus);
		return response.setComplete();
	}

	private void populateRequestWithHeaders(ServerWebExchange exchange, String token) {
		Claims claims = jwtUtil.extractAllClaims(token);
		exchange.getRequest().mutate().header("id", String.valueOf(claims.get("id")))
				.header("role", String.valueOf(claims.get("role"))).build();
	}

	private String getAuthHeader(ServerHttpRequest request) {
		return request.getHeaders().getOrEmpty("Authorization").get(0);
	}

	private boolean isAuthMissing(ServerHttpRequest request) {
		return !request.getHeaders().containsKey("Authorization");
	}

}
