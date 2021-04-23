package org.enumapi.app.service;

import org.enumapi.app.dto.SimpleRequest;
import org.enumapi.app.dto.SimpleResponse;
import org.enumapi.app.enums.PreparedOccupation;
import org.springframework.stereotype.Service;

import static org.enumapi.app.TextConstant.IT_IS_NOT_OK;
import static org.enumapi.app.TextConstant.IT_IS_NOT_SAME_OCCUPATION_TYPE;
import static org.enumapi.app.TextConstant.IT_S_OK;
import static org.enumapi.app.TextConstant.IT_S_SAME_OCCUPATION_TYPE;
import static org.enumapi.app.TextConstant.SOME_IMPORTANT_DATA;

@Service
public class SomeServiceImpl implements SomeService {

    private final AppStubService appStubService;

    public SomeServiceImpl(AppStubService appStubService) {
        this.appStubService = appStubService;
    }

    @Override
    public String makeDecisionBasedOnOccupation(SimpleRequest request) {
        if (request.getOccupation() == PreparedOccupation.WORKER) {
            return IT_S_OK;
        }
        return IT_IS_NOT_OK;
    }

    @Override
    public SimpleResponse enrichApplication(SimpleRequest request) {
        return new SimpleResponse(request.getOccupation(), SOME_IMPORTANT_DATA);
    }

    @Override
    public String compareWithExistingState(SimpleRequest request) {
        final var response = appStubService.askSomeMicroService(PreparedOccupation.WORKER.name());
        if (request.getOccupation() == response.getOccupation()) {
            return IT_S_SAME_OCCUPATION_TYPE;
        }
        return IT_IS_NOT_SAME_OCCUPATION_TYPE;
    }

    @Override
    public String compareWithNotExistingState(SimpleRequest request) {
        final var response = appStubService.askSomeMicroService("GAMER");
        if (request.getOccupation() == response.getOccupation()) {
            return IT_S_SAME_OCCUPATION_TYPE;
        }
        return IT_IS_NOT_SAME_OCCUPATION_TYPE;
    }
}
