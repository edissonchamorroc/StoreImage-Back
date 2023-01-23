package com.springapirest.storeimages.controller;

import com.springapirest.storeimages.service.StorageService;
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

    @PostMapping
    public ResponseEntity<?> uploadImage(@RequestParam("image") MultipartFile file) throws IOException {

        String uploadImg=storageService.uploadImage(file);

        return ResponseEntity.status(HttpStatus.OK).body(uploadImg);

    }

    @GetMapping(value ="/{fileName}",produces = {MediaType.IMAGE_PNG_VALUE,MediaType.IMAGE_JPEG_VALUE})
    public ResponseEntity<?> downloadImage(@PathVariable String fileName) throws IOException {
        byte[] downloadImg=storageService.downloadImage(fileName);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/jpg"))
                .contentType(MediaType.valueOf("image/png"))
                .body(downloadImg);
    }

    @GetMapping(value = "/", produces = {MediaType.IMAGE_PNG_VALUE,MediaType.IMAGE_JPEG_VALUE})
    public ResponseEntity<?> downloadImages() throws IOException {
        List<byte[]> downloadImgs=storageService.downloadImages();
        return ResponseEntity.status(HttpStatus.OK)
                .body(downloadImgs.get(0));
    }

    @DeleteMapping(value = "/{fileName}",produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> deleteImage(@PathVariable String fileName) throws IOException {
        storageService.deleteImageByName(fileName);
        String response = "{'response':'Imagen eliminada con éxito'}";
        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }

}
