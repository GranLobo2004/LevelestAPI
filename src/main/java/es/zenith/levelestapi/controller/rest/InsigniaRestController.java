package es.zenith.levelestapi.controller.rest;

import es.zenith.levelestapi.dto.InsigniaDTO;
import es.zenith.levelestapi.service.InsigniaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

@RestController
@RequestMapping("/insignias")
public class InsigniaRestController {

    @Autowired
    private InsigniaService insigniaService;

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
}
