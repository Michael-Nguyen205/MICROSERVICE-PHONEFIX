//package com.tanthanh.apigateway.filter;
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.cloud.gateway.filter.GatewayFilterChain;
//import org.springframework.cloud.gateway.filter.GlobalFilter;
//import org.springframework.core.annotation.Order;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.HttpStatus;
//import org.springframework.stereotype.Component;
//import org.springframework.web.server.ServerWebExchange;
//import reactor.core.publisher.Mono;
//
//@Component
//@Order(1) // Optional: define the order of this filter
//public class JwtAuthFilter implements GlobalFilter {
//
//    @Value("${jwt.secret}")
//    private String SECRET_KEY;
//
//    @Override
//    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
//        // Lấy token từ header
//        String token = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
//
//        // Xác thực và phân quyền
//        if (token != null && token.startsWith("Bearer ")) {
//            token = token.substring(7); // Loại bỏ "Bearer " khỏi token
//            if (validateToken(token)) {
//                String username = getUsernameFromToken(token);
//                // Xác định quyền truy cập dựa trên đường dẫn và phương thức
//                String path = exchange.getRequest().getURI().getPath();
//                HttpMethod method = exchange.getRequest().getMethod();
//
//                if (path.startsWith("/api/v1/phonefix") && method == HttpMethod.POST) {
//                    if (hasRole(username, "ROLE_STAFF")) {
//                        return chain.filter(exchange);
//                    }
//                } else if (path.startsWith("/api/v1/phone") && method == HttpMethod.POST) {
//                    if (hasRole(username, "ROLE_ADMIN")) {
//                        return chain.filter(exchange);
//                    }
//                }
//
//                // Nếu không có quyền, trả về lỗi Forbidden
//                exchange.getResponse().setStatusCode(HttpStatus.FORBIDDEN);
//                return exchange.getResponse().setComplete();
//            }
//        }
//
//        // Nếu không hợp lệ hoặc không có token, trả về lỗi Unauthorized
//        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
//        return exchange.getResponse().setComplete();
//    }
//
//    private boolean validateToken(String token) {
//        try {
//            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
//            return true;
//        } catch (Exception e) {
//            return false;
//        }
//    }
//
//    private String getUsernameFromToken(String token) {
//        Claims claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
//        return claims.getSubject();
//    }
//
//    private boolean hasRole(String username, String role) {
//        // Logic để kiểm tra quyền dựa trên username
//        // Ví dụ: lấy từ cơ sở dữ liệu hoặc dịch vụ xác thực
//        // Ở đây giả sử bạn có một phương thức kiểm tra quyền đơn giản
//        return true; // Thay đổi logic kiểm tra quyền
//    }
//}
