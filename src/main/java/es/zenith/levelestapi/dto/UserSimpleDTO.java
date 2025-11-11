package es.zenith.levelestapi.dto;
import java.util.Collection;

public record UserSimpleDTO(
        Long id,
        String username,
        String name,
        String surname,
        String email,
        Collection<String> roles
){}
