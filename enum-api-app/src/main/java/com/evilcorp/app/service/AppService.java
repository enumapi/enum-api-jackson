package com.evilcorp.app.service;


import com.evilcorp.app.dto.SimpleResponse;

public interface AppService {

    SimpleResponse askSomeMicroService(String type);
}
