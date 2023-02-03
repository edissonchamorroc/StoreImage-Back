package com.springapirest.storeimages.controller;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.springapirest.storeimages.message.ResponseMessage;
import com.springapirest.storeimages.service.StorageService;
import com.springapirest.storeimages.util.Messages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/image")
public class StorageController {

    @Autowired
    private StorageService storageService;


    @PostMapping
    public ResponseEntity<?> uploadImage(@RequestParam(value = "image",required = false) MultipartFile file) {

        try {

            String fileName = storageService.uploadImage(file);

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseMessage(
                            HttpStatus.OK.value(),
                            Messages.UPLOAD_SUCCESFULLY.getMessage(),
                            fileName
                    ));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @GetMapping(value = "/{fileName}")
    public ResponseEntity<?> downloadImage(@PathVariable String fileName) {

        List<String> content = new ArrayList<>();

        try {
            content.add(storageService.downloadImage(fileName).toString());

            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseMessage(
                            HttpStatus.OK.value(),
                            Messages.GET_SUCCESFULLY.getMessage(),
                            Messages.URI_GET.getMessage() + fileName,
                             content
                    ));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping(value = "/")
    public ResponseEntity<?> downloadImages() {

        try {

            List<String> content = storageService.downloadImages();

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseMessage(
                            HttpStatus.OK.value(),
                            Messages.GET_SUCCESFULLY.getMessage(),
                            Messages.URI.getMessage()+"/view/imageName",
                            content

                    ));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
    @GetMapping(value = "/view/{fileName}", produces = {MediaType.IMAGE_PNG_VALUE,MediaType.IMAGE_PNG_VALUE})
    public ResponseEntity<?> viewImage(@PathVariable String fileName) {

        try {
            byte[] content= storageService.downloadImage(fileName);

            return ResponseEntity.status(HttpStatus.OK)
                    .body(content);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @DeleteMapping(value = "/{fileName}")
    public ResponseEntity<?> deleteImage(@PathVariable String fileName) throws IOException {

        try {
            boolean isDeleted = storageService.deleteImageByName(fileName);

            if (isDeleted) {


                return ResponseEntity.status(HttpStatus.OK)
                        .body(new ResponseMessage(
                                HttpStatus.OK.value(),
                                Messages.DELETE_SUCCESFULLY.getMessage(),
                                null
                        ));

            }

            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseMessage(
                            HttpStatus.NOT_FOUND.value(),
                            Messages.DELETE_ERROR.getMessage(),
                            null
                    ));

        } catch (Exception e) {

            throw new RuntimeException(e);

        }

    }

}
