package es.zenith.levelestapi.controller.rest;

import es.zenith.levelestapi.domain.Image;
import es.zenith.levelestapi.dto.ImageDTO;
import es.zenith.levelestapi.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/images")
public class ImageRestController {

    @Autowired
    ImageService imageService;

    private static final Map<String, String> EXTENSIONS = Map.of(
            ".jpg", "image/jpeg",
            ".png", "image/png",
            ".gif", "image/gif"
    );

    @GetMapping("/{id}")
    public ResponseEntity<Object> getImage(@PathVariable long id){
        ImageDTO imageDTO = imageService.getInsignia(id);
        Resource imageAPI = imageService.loadImage(imageDTO.id());
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, EXTENSIONS.get(imageDTO.contentType())).body(imageAPI);
    }
}
