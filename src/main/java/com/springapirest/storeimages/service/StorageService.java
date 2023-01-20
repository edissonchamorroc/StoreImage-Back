package com.springapirest.storeimages.service;

import com.springapirest.storeimages.entity.ImageData;
import com.springapirest.storeimages.respository.StorageRepository;
import com.springapirest.storeimages.util.ImageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public class StorageService {

    @Autowired
    private StorageRepository storageRepository;

    public String uploadImage(MultipartFile file) throws IOException {
        if (!storageRepository.findByName(file.getOriginalFilename()).isPresent()) {
            ImageData imageData = storageRepository.save(
                    ImageData.builder()
                    .name(file.getOriginalFilename())
                    .type(file.getContentType())
                    .imgdata(ImageUtils.compressImage(file.getBytes())).build());
            if (imageData != null) {
                return file.getOriginalFilename();
            }
        }
        return file.getOriginalFilename();
    }

    public byte[] downloadImage(String fileName) {

        Optional<ImageData> dbImageData = storageRepository.findByName(fileName);
        byte[] images = ImageUtils.decompressImage(dbImageData.get().getImgdata());
        return images;
    }
}
