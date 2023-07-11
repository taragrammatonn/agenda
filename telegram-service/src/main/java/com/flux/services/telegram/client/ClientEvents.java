package com.flux.services.telegram.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "agenda-service")
public interface ClientEvents {

    @GetMapping("/agenda/getAllEvents")
    List<?> getAllEvents();
}
