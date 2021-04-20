package com.evilcorp.app.service;


import com.evilcorp.app.dto.SimpleRequest;
import com.evilcorp.app.dto.SimpleResponse;

public interface SomeService {
    String makeDecisionBasedOnOccupation(SimpleRequest request);
    SimpleResponse enrichApplication(SimpleRequest request);
    String compareWithExistingState(SimpleRequest request);
    String compareWithNotExistingState(SimpleRequest request);
}
