package com.springapirest.storeimages.util;

public enum Messages {

    DELETE_SUCCESFULLY("Deleted the image successfully"),
    UPLOAD_SUCCESFULLY("Upload Successfully"),
    GET_SUCCESFULLY("Got the image successfully"),
    IMAGE_NOT_EXIST("The image does not exist!"),
    URI("http://52.90.175.102/api/image/"),
    URI_GET("http://52.90.175.102/api/image/view/")

    ;
    private String message;

    Messages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
