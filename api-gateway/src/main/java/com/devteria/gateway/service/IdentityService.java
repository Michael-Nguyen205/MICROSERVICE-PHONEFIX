package com.devteria.gateway.service;

import com.devteria.gateway.dto.ApiResponse;
import com.devteria.gateway.dto.request.IntrospectRequest;
import com.devteria.gateway.dto.response.IntrospectResponse;
import com.devteria.gateway.repository.IdentityClient;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;


@Log4j2
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class IdentityService {
    IdentityClient identityClient;


    public Mono<ApiResponse<IntrospectResponse>> introspect(String token){
        log.error("token {} :",token);

        IntrospectRequest introspectRequest =   IntrospectRequest.builder()
                .token(token)
                .build();

        log.error("introspectRequest {} :",introspectRequest.toString());

        return identityClient.introspect(introspectRequest)
                .doOnError(error -> log.error("Error không gửi đc webclient", error));


    }
}
