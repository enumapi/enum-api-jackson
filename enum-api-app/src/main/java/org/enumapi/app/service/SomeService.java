package org.enumapi.app.service;


import org.enumapi.app.dto.SimpleRequest;
import org.enumapi.app.dto.SimpleResponse;

public interface SomeService {
    String makeDecisionBasedOnOccupation(SimpleRequest request);
    SimpleResponse enrichApplication(SimpleRequest request);
    String compareWithExistingState(SimpleRequest request);
    String compareWithNotExistingState(SimpleRequest request);
}
