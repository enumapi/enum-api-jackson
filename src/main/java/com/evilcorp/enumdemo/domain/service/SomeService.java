package com.evilcorp.enumdemo.domain.service;

import com.evilcorp.enumdemo.domain.dto.SimpleRequest;
import com.evilcorp.enumdemo.domain.dto.SimpleResponse;

public interface SomeService {
    String makeDecisionBasedOnOccupation(SimpleRequest request);
    SimpleResponse enrichApplication(SimpleRequest request);
    String compareWithExistingState(SimpleRequest request);
    String compareWithNotExistingState(SimpleRequest request);
}
