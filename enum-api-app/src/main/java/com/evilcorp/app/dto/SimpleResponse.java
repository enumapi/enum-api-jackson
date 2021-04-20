package com.evilcorp.app.dto;


public class SimpleResponse {
    private Occupation occupation;

    private String someImportantData;

    public SimpleResponse() {}

    public SimpleResponse(Occupation occupation, String someImportantData) {
        this.occupation = occupation;
        this.someImportantData = someImportantData;
    }

    public Occupation getOccupation() {
        return occupation;
    }

    public void setOccupation(Occupation occupation) {
        this.occupation = occupation;
    }

    public String getSomeImportantData() {
        return someImportantData;
    }

    public void setSomeImportantData(String someImportantData) {
        this.someImportantData = someImportantData;
    }
}
