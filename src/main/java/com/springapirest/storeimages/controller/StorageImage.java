package com.springapirest.storeimages.controller;

import com.springapirest.storeimages.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@CrossOrigin(origins = "https://storeimage.vercel.app/")
@RestController
@RequestMapping("/api/image")
public class StorageImage {

    @Autowired
    private StorageService storageService;

    @PostMapping
    public ResponseEntity<?> uploadImage(@RequestParam("image") MultipartFile file) throws IOException {

        String uploadImg=storageService.uploadImage(file);

        return ResponseEntity.status(HttpStatus.OK).body(uploadImg);

    }

    @GetMapping("/{fileName}")
    public ResponseEntity<?> downloadImagePng(@PathVariable String fileName) throws IOException {
        byte[] downloadImg=storageService.downloadImage(fileName);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/jpg"))
                .contentType(MediaType.valueOf("image/png"))
                .body(downloadImg);
    }

}
