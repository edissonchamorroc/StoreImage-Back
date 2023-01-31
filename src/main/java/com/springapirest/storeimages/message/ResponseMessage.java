package com.springapirest.storeimages.message;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


public class ResponseMessage {

    @JsonProperty
    @Getter @Setter
    private int status;
    @JsonProperty
    @Getter @Setter
    private String message;
    @JsonProperty
    @Getter @Setter
    private String path;
    @JsonProperty
    @Getter @Setter
    private List<byte[]> content;

    public  ResponseMessage(int status,String message,String path){
        this.status=status;
        this.message=message;
        this.path=path;
    }

    public  ResponseMessage(int status,String message,String path, List<byte[]> content){
        this.status=status;
        this.message=message;
        this.path=path;
        this.content=content;
    }


}
