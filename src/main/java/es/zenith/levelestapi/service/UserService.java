package es.zenith.levelestapi.service;

import es.zenith.levelestapi.domain.Insignia;
import es.zenith.levelestapi.domain.Level;
import es.zenith.levelestapi.domain.User;
import es.zenith.levelestapi.dto.*;
import es.zenith.levelestapi.mapper.InsigniaMapper;
import es.zenith.levelestapi.mapper.LevelMapper;
import es.zenith.levelestapi.mapper.UserMapper;
import es.zenith.levelestapi.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;


@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private LevelMapper levelMapper;

    @Autowired
    private InsigniaMapper insigniaMapper;

    @Autowired
    private InsigniaService insigniaService;

    @Autowired
    private LevelService levelService;


    @PostConstruct
    public void init() {
        if (userRepository.count()>0) return;
        List<User> users = List.of(
                new User("Victor","12345","Victor Hugo","OLIVEIRA","victor@gmail.com", List.of("USER", "DEVELOPER") ),
                new User("Naroa","12345","Naroa","Martín","naroa@gmail.com", List.of("USER", "DEVELOPER") ),
                new User("David","12345","David","Martín","david@gmail.com", List.of("USER", "DEVELOPER") ),
                new User("Jaime","12345","Jaime","Ochoa de Alda","jaime@gmail.com", List.of("USER", "DEVELOPER") ),
                new User("Nerea","12345","Nerea Tindary","Morocho","nerea@gmail.com", List.of("USER", "DEVELOPER") ),
                new User("Samuel","12345","Samuel","Melían","samuel@gmail.com", List.of("USER", "DEVELOPER") )
                );
        for (User user : users) {
            userRepository.save(user);
        }
    }

    public List<UserSimpleDTO> getUsers() {
        List<UserSimpleDTO> users = new ArrayList<>();
        for(User u : userRepository.findAll()) {
            users.add(userMapper.toSimpleDTO(u));
        }
        return users;
    }

    public UserDTO getUser(long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new NoSuchElementException("User not found"));
        return userMapper.toDTO(user);
    }

    @Transactional
    public UserDTO saveUser(UserFormDTO userFormDTO) {
        User user = new  User(userFormDTO.username(), userFormDTO.password(), userFormDTO.name(), userFormDTO.surname(), userFormDTO.email(), List.of("USER"));
        return userMapper.toDTO(userRepository.save(user));
    }

    @Transactional
    public UserDTO updateUser(long id, UserFormDTO userFormDTO) {
        User user = userRepository.findById(id).orElseThrow(() -> new NoSuchElementException("User not found"));
        user.setUsername(userFormDTO.username());
        user.setPassword(userFormDTO.password());
        user.setName(userFormDTO.name());
        user.setSurname(userFormDTO.surname());
        user.setEmail(userFormDTO.email());
        return userMapper.toDTO(userRepository.save(user));
    }

    @Transactional
    public UserDTO deleteUser(long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new NoSuchElementException("User not found"));
        userRepository.delete(user);
        return userMapper.toDTO(user);
    }

    public List<LevelSimpleDTO> getLevels(long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new NoSuchElementException("User not found"));
        List<LevelSimpleDTO> levels = new ArrayList<>();
        for(Level level: user.getCompletedLevels()){
            levels.add(levelMapper.toSimpleDTO(level));
        }
        return levels;
    }

    @Transactional
    public LevelDTO addCompletedLevels(long id, long levelId) {
        User user = userRepository.findById(id).orElseThrow(() -> new NoSuchElementException("User not found"));
        LevelDTO levelDTO = levelService.getLevel(levelId);
        Level level = levelMapper.toEntity(levelDTO);
        boolean containsLevel = false;
        for (Level l: user.getCompletedLevels()){
            if (l.getId().equals(level.getId())){
                containsLevel = true;
            }
        }
        if (!containsLevel) user.addCompletedLevels(level);
        userRepository.save(user);
        return levelDTO;
    }

    public List<InsigniaDTO> getInsignias(long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new NoSuchElementException("User not found"));
        List<InsigniaDTO> insignias = new ArrayList<>();
        for(Insignia insignia: user.getInsignias()){
            insignias.add(insigniaMapper.toDTO(insignia));
        }
        return insignias;
    }

    @Transactional
    public InsigniaDTO addInsignia(long id, long insigniaId) {
        User user = userRepository.findById(id).orElseThrow(() -> new NoSuchElementException("User not found"));
        Insignia insignia = insigniaService.findInsignia(insigniaId);
        user.addInsignia(insignia);
        userRepository.save(user);
        return insigniaMapper.toDTO(insignia);
    }

    public UserDTO login(LoginForm loginForm) {
        User user = userRepository.findByEmail(loginForm.getEmail()).orElseThrow(() -> new NoSuchElementException("User not found"));
        if (user.getPassword().equals(loginForm.getPassword())) {
            return userMapper.toDTO(user);
        }
        return null;
    }

    public UserDTO addRoletoUser(long id, String role) {
        User user = userRepository.findById(id).orElseThrow(() -> new NoSuchElementException("User not found"));
        user.addRole(role.toUpperCase());
        return userMapper.toDTO(userRepository.save(user));
    }
}