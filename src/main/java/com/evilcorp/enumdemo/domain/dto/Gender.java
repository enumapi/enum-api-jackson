package com.evilcorp.enumdemo.domain.dto;

import com.evilcorp.enumdemo.domain.EnumApi;
import com.evilcorp.enumdemo.domain.enums.PreparedGender;

@EnumApi(enumClass = PreparedGender.class)
public interface Gender {
}
