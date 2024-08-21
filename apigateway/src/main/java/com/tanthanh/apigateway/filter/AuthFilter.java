//package com.tanthanh.apigateway.filter;
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.HttpStatus;
//import reactor.core.publisher.Mono;
//import org.springframework.web.server.ServerWebExchange;
//import org.springframework.cloud.gateway.filter.GatewayFilter;
//import org.springframework.cloud.gateway.filter.GatewayFilterChain;
//
//import java.util.List;
//
//public class AuthFilter implements GatewayFilter {
//
//    private static final String SECRET_KEY = "L7qNvHLfGIE+pLGRecsOl1/ZUS7ky8MGHh7URUtvC9p3NFF8cRGTnMXzipHjWKcGrZAs7KX+rbMjexgVECGugQ==";
//
//    @Override
//    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
//        String token = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
//
//        if (token != null && validateToken(token)) {
//            String username = getUsernameFromToken(token);
//            Claims claims = getClaimsFromToken(token);
//
//            // Kiểm tra vai trò dựa trên đường dẫn và phương thức
//            String path = exchange.getRequest().getURI().getPath();
//            HttpMethod method = exchange.getRequest().getMethod();
//
//            if (path.startsWith("/api/v1/phonefix") && method == HttpMethod.POST) {
//                if (hasRole(claims, "ROLE_STAFF")) {
//                    return chain.filter(exchange);
//                }
//            } else if (path.startsWith("/api/v1/phone") && method == HttpMethod.POST) {
//                if (hasRole(claims, "ROLE_ADMIN")) {
//                    return chain.filter(exchange);
//                }
//            }
//
//            // Nếu không có quyền, trả về lỗi Forbidden
//            exchange.getResponse().setStatusCode(HttpStatus.FORBIDDEN);
//            return exchange.getResponse().setComplete();
//        }
//
//        // Nếu không hợp lệ hoặc không có token, trả về lỗi Unauthorized
//        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
//        return exchange.getResponse().setComplete();
//    }
//
//    private boolean validateToken(String token) {
//        try {
//            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token.replace("Bearer ", ""));
//            return true;
//        } catch (Exception e) {
//            return false;
//        }
//    }
//
//    private String getUsernameFromToken(String token) {
//        Claims claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token.replace("Bearer ", "")).getBody();
//        return claims.getSubject();
//    }
//
//    private Claims getClaimsFromToken(String token) {
//        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token.replace("Bearer ", "")).getBody();
//    }
//
//    private boolean hasRole(Claims claims, String role) {
//        // Lấy danh sách vai trò từ claims
//        @SuppressWarnings("unchecked")
//        List<String> roles = (List<String>) claims.get("roles");
//
//        // Kiểm tra xem vai trò có trong danh sách hay không
//        return roles != null && roles.contains(role);
//    }
//}
