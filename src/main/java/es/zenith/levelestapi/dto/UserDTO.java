package es.zenith.levelestapi.dto;

import java.time.LocalDateTime;
import java.util.Collection;

public record UserDTO(
    Long id,
    LocalDateTime creationDate,
    String username,
    String name,
    String surname,
    String email,
    Collection<String> roles,
    Collection<InsigniaDTO> insignias,
    Collection<LevelSimpleDTO> completedLevels
){}
