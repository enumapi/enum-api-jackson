package org.enumapi.app.service;


import org.enumapi.app.dto.SimpleResponse;

public interface AppService {

    SimpleResponse askSomeMicroService(String type);
}
