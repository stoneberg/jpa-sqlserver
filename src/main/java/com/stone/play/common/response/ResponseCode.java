package com.stone.play.common.response;

public enum ResponseCode {
    // default code, message
    SUCCESS("SUCCESS", "API Request has been successfully completed."),
    FAILURE("FAILURE", "API Request has failed.");

    String code;
    String message;

    ResponseCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
