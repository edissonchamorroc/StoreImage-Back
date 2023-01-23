package com.springapirest.storeimages.controller;

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
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/image")
public class StorageController {

    @Autowired
    private StorageService storageService;

    private String message="";

    @PostMapping
    public ResponseEntity<?> uploadImage(@RequestParam("image") MultipartFile file) throws IOException {

        String uploadImg=storageService.uploadImage(file);

        return ResponseEntity.status(HttpStatus.OK).body(uploadImg);

    }

    @GetMapping(value ="/{fileName}",produces = {MediaType.IMAGE_PNG_VALUE,MediaType.IMAGE_JPEG_VALUE})
    public ResponseEntity<?> downloadImage(@PathVariable String fileName) throws IOException {
        byte[] downloadImg=storageService.downloadImage(fileName);
        return ResponseEntity.status(HttpStatus.OK)
                .body(downloadImg);
    }

    @GetMapping(value = "/")
    public ResponseEntity<?> downloadImages() throws IOException {
        List<String> downloadImgs=storageService.downloadImages();
        return new ResponseEntity<>(downloadImgs,HttpStatus.OK);
    }

    @DeleteMapping(value = "/{fileName}")
    public ResponseEntity<?> deleteImage(@PathVariable String fileName) throws IOException {

        try {
            boolean isDeleted = storageService.deleteImageByName(fileName);

            if (isDeleted) {

                message = Messages.DELETE_SUCCESFULLY.getMessage() + fileName;
                return ResponseEntity.status(HttpStatus.OK)
                        .body(new ResponseMessage(message));

            }

            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseMessage(Messages.IMAGE_NOT_EXIST.getMessage()));

        } catch (Exception e) {

            message = Messages.DELETE_ERROR.getMessage()
                    + fileName
                    + Messages.ERROR.getMessage()
                    + e.getMessage();

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseMessage(message));
        }

    }

}
