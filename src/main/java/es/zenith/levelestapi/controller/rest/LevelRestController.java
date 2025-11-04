package es.zenith.levelestapi.controller.rest;

import es.zenith.levelestapi.dto.LevelDTO;
import es.zenith.levelestapi.dto.LevelSimpleDTO;
import es.zenith.levelestapi.service.LevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

@RestController
@RequestMapping("/levels")
public class LevelRestController {

    @Autowired
    private LevelService levelService;

    @GetMapping("/")
    public List<LevelSimpleDTO> getLevels(){
        return levelService.getAll();
    }

    @GetMapping("/{id}")
    public LevelDTO getLevel(@PathVariable long id){
        return levelService.getLevel(id);
    }

    @PostMapping("/")
    public ResponseEntity<LevelDTO> createLevel(@RequestBody LevelDTO levelDTO){
        LevelDTO savedLevelDTO = levelService.saveLevel(levelDTO);
        URI location = fromCurrentRequest().path("/{id}").buildAndExpand(savedLevelDTO.id()).toUri();
        return ResponseEntity.created(location).body(savedLevelDTO);
    }

    @PutMapping("/{id}")
    public LevelDTO updateLevel(@PathVariable long id, @RequestBody LevelDTO levelDTO){
        LevelDTO updatedLevel = levelService.updateLevel(levelDTO, id);
        return updatedLevel;
    }

    @DeleteMapping("/{id}")
    public LevelDTO deleteLevel(@PathVariable long id){
        return levelService.deleteLevel(id);
    }
}
