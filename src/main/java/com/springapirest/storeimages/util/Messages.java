package com.springapirest.storeimages.util;

public enum Messages {
    DELETE_SUCCESFULLY("Delete the image successfully: "),
    IMAGE_NOT_EXIST("The image does not exist!"),
    DELETE_ERROR("Could not delete the image: "),
    ERROR(" Error ")
    ;
    private String message;

    Messages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
