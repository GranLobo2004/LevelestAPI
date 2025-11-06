package es.zenith.levelestapi.dto;


import es.zenith.levelestapi.domain.Insignia;
import es.zenith.levelestapi.domain.Level;

import java.time.LocalDateTime;
import java.util.List;

public record UserDTO(
    Long id,
    LocalDateTime creationDate,
    String username,
    String name,
    String surname,
    String email,
    List<String> roles,
    List<InsigniaDTO> insignias,
    List<LevelSimpleDTO> completedLevels
){}
