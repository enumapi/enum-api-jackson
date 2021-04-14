package com.evilcorp.enumdemo.domain.service;

import com.evilcorp.enumdemo.domain.dto.SimpleResponse;

public interface AppService {

    SimpleResponse askSomeMicroService(String type);
}
