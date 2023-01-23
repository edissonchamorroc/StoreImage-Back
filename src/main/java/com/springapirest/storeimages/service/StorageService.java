package com.springapirest.storeimages.service;

import com.springapirest.storeimages.entity.StorageEntity;
import com.springapirest.storeimages.respository.StorageRepository;
import com.springapirest.storeimages.util.ImageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StorageService {

    @Autowired
    private StorageRepository storageRepository;

    public String uploadImage(MultipartFile file) throws IOException {
        if (!storageRepository.findByName(file.getOriginalFilename()).isPresent()) {
            StorageEntity imageData = storageRepository.save(
                    StorageEntity.builder()
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

        Optional<StorageEntity> dbImageData = storageRepository.findByName(fileName);
        byte[] images = ImageUtils.decompressImage(dbImageData.get().getImgdata());
        return images;
    }

    public List<byte[]> downloadImages() {

        List<StorageEntity> dbImageData = storageRepository.findAll();
        List<byte[]> images = new ArrayList<>();
        for(StorageEntity data:dbImageData){
            images.add(ImageUtils.decompressImage(data.getImgdata()));
        }
        return images;
    }

    public void deleteImageByName(String fileName) {
        if(storageRepository.findByName(fileName).isPresent()){
            storageRepository.deleteByName(fileName);
        }
    }
}
