package es.zenith.levelestapi.controller.rest;

import es.zenith.levelestapi.dto.InsigniaDTO;
import es.zenith.levelestapi.service.ImageService;
import es.zenith.levelestapi.service.InsigniaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

@RestController
@RequestMapping("/insignias")
public class InsigniaRestController {

    private static final Map<String, String> EXTENSIONS = Map.of(
            ".jpg", "image/jpeg",
            ".png", "image/png",
            ".gif", "image/gif"
    );

    @Autowired
    private InsigniaService insigniaService;

    @Autowired
    private ImageService imageService;

    @GetMapping("/")
    public List<InsigniaDTO> getAllInsignias(){
        return insigniaService.getAll();
    }

    @GetMapping("/{id}")
    public InsigniaDTO getInsigniaById(@PathVariable long id){
        return insigniaService.getInsignia(id);
    }

    @PostMapping("/")
    public ResponseEntity<InsigniaDTO> createInsignia(@RequestBody InsigniaDTO insigniaDTO){
        InsigniaDTO savedInsigniaDTO =  insigniaService.saveInsignia(insigniaDTO);
        URI location = fromCurrentRequest().path("/{id}").buildAndExpand(savedInsigniaDTO.id()).toUri();
        return ResponseEntity.created(location).body(savedInsigniaDTO);
    }

    @PutMapping("/{id}")
    public InsigniaDTO updateInsignia(@PathVariable long id, @RequestBody InsigniaDTO insigniaDTO){
        return insigniaService.updateInsignia(id, insigniaDTO);
    }

    @DeleteMapping("/{id}")
    public InsigniaDTO deleteInsignia(@PathVariable long id){
        return insigniaService.deleteInsignia(id);
    }

    @GetMapping("/{id}/image")
    public ResponseEntity<Object> getInsigniaImage(@PathVariable long id){
        try{
            InsigniaDTO insigniaDTO = insigniaService.getInsignia(id);
            Resource imageAPI = imageService.loadImage(insigniaDTO.image().id());
            return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, EXTENSIONS.get(insigniaDTO.image().contentType())).body(imageAPI);
        } catch(NoSuchElementException e){
            return null;
        }
    }

    @PostMapping("/{id}/image")
    public InsigniaDTO  uploadInsigniaImage(@PathVariable long id, @RequestBody MultipartFile file){
        return insigniaService.saveImage(id, file);
    }

}
