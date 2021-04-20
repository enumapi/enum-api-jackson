package com.evilcorp.app.controller;

import com.evilcorp.app.dto.SimpleRequest;
import com.evilcorp.app.dto.SimpleResponse;
import com.evilcorp.app.enums.PreparedGender;
import com.evilcorp.app.service.SomeService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    private final SomeService service;

    public Controller(SomeService service) {
        this.service = service;
    }

    @PostMapping("/make-decision")
    public String makeDecisionBasedOnOccupation(@RequestBody SimpleRequest request) {
        return service.makeDecisionBasedOnOccupation(request);
    }

    @PostMapping("/enrich-application")
    public SimpleResponse enrichApplication(@RequestBody SimpleRequest request) {
        return service.enrichApplication(request);
    }

    @PostMapping("/compare-with-existingState")
    public String compareWithExistingState(@RequestBody SimpleRequest request) {
        if (request.getGender() == PreparedGender.FEMALE) {
            return service.compareWithNotExistingState(request);
        }
        return service.compareWithExistingState(request);
    }
}
