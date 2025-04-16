package com.tracking.web.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResponseError {

    @JsonProperty("code")
    private String code;

    @JsonProperty("error")
    private String error;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
