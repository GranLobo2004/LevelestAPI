package es.zenith.levelestapi.dto;
import java.util.List;

public record UserSimpleDTO(
        Long id,
        String username,
        String name,
        String surname,
        List<String> Roles
){}
