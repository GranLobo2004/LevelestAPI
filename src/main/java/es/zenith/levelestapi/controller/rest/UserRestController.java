package es.zenith.levelestapi.controller.rest;

import es.zenith.levelestapi.dto.*;
import es.zenith.levelestapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;


@RestController
@RequestMapping("/users")
public class UserRestController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    @Deprecated
    public UserDTO login(@RequestBody LoginForm loginForm){
        return userService.login(loginForm);
    }

    @GetMapping("/")
    public List<UserSimpleDTO> getUsers(){
       return userService.getUsers();
    }

    @GetMapping("/me")
    public UserDTO getCurrentUser(){
        return userService.getLoggedInUser();
    }

    @GetMapping("/{id}")
    public UserDTO getUser(@PathVariable long id){
        return userService.getUser(id);
    }

    @PostMapping("/")
    public ResponseEntity<UserDTO> createUser(@RequestBody UserFormDTO userFormDTO){
        UserDTO userDTO = userService.saveUser(userFormDTO);
        URI location = fromCurrentRequest().path("/{id}").buildAndExpand(userDTO.id()).toUri();
        return ResponseEntity.created(location).body(userDTO);
    }

    @PutMapping("/{id}")
    public UserDTO updateUser(@PathVariable long id, @RequestBody UserFormDTO userFormDTO){
        return userService.updateUser(id, userFormDTO);
    }

    @DeleteMapping("/{id}")
    public UserDTO deleteUser(@PathVariable long id){
        return userService.deleteUser(id);
    }

    @GetMapping("/{id}/completedLevels")
    public List<LevelSimpleDTO> getLevelsCompleted(@PathVariable long id){
        return userService.getLevels(id, true);
    }

    @PostMapping("/{id}/completedLevels")
    public LevelDTO createLevelsCompleted(@PathVariable long id, @RequestBody long levelId){
        return userService.addLevel(id, levelId, true);
    }

    @GetMapping("/{id}/failedLevels")
    public List<LevelSimpleDTO> getfailedLevels(@PathVariable long id){
        return userService.getLevels(id, false);
    }

    @PostMapping("/{id}/failedLevels")
    public LevelDTO createfailedLevels(@PathVariable long id, @RequestBody long levelId){
        return userService.addLevel(id, levelId, false);
    }

    @GetMapping("/{id}/insignias")
    public List<InsigniaDTO> getInsignias(@PathVariable long id){
        return userService.getInsignias(id);
    }

    @PostMapping("/{id}/insignias")
    public InsigniaDTO addInsignia(@PathVariable long id, @RequestBody long insigniaId){
        return userService.addInsignia(id, insigniaId);
    }

    @PostMapping("/{id}/roles")
    public UserDTO addRole(@PathVariable long id, @RequestBody String role){
        return userService.addRoletoUser(id, role);
    }
}
