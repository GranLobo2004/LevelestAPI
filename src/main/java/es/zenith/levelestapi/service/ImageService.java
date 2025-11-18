package es.zenith.levelestapi.service;

import es.zenith.levelestapi.domain.Image;
import es.zenith.levelestapi.dto.ImageDTO;
import es.zenith.levelestapi.mapper.ImageMapper;
import es.zenith.levelestapi.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
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

    public ImageDTO getInsignia(long id) {
        Image image = imageRepository.findById(id).orElseThrow(()->new NoSuchElementException("Image not found."));
        return  imageMapper.toDTO(image);
    }

    public static MultipartFile getImageAsMultipart(String imageName) throws IOException {
        // Cargar el archivo desde resources/static/Insignias/Images
        ClassPathResource resource = new ClassPathResource("static/Images/" + imageName);

        if (!resource.exists()) {
            throw new IOException("No se encontró la imagen: " + imageName);
        }

        // Detectar tipo MIME según la extensión
        String mimeType = URLConnection.guessContentTypeFromName(imageName);
        if (mimeType == null) {
            mimeType = "application/octet-stream"; // fallback genérico
        }

        // Crear MultipartFile usando el contenido del recurso
        try (InputStream inputStream = resource.getInputStream()) {
            return new MockMultipartFile(
                    "file",         // nombre del parámetro
                    imageName,      // nombre original
                    mimeType,       // tipo MIME
                    inputStream     // contenido binario
            );
        }
    }
}
