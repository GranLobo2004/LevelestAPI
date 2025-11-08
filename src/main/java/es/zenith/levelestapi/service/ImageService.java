package es.zenith.levelestapi.service;

import es.zenith.levelestapi.domain.Image;
import es.zenith.levelestapi.dto.ImageDTO;
import es.zenith.levelestapi.mapper.ImageMapper;
import es.zenith.levelestapi.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.SQLException;
import java.util.NoSuchElementException;

@Service
public class ImageService {

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private ImageMapper imageMapper;

    public Resource loadImage(long id) {
        Image image = imageRepository.findById(id).orElseThrow();
        try {
            return new InputStreamResource(image.getimageFile().getBinaryStream());
        } catch (SQLException e) {
            throw new NoSuchElementException();
        }
    }

    @Transactional
    public Image saveImage(MultipartFile rawImage) {
        try{
            Image newImage = new Image(rawImage);
            return imageRepository.save(newImage);
        }
        catch(Exception e){
            return null;
        }
    }
}
