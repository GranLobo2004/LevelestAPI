package es.zenith.levelestapi.dto;

import es.zenith.levelestapi.Enumeration.Subject;

public record LevelSimpleDTO(
        Long id,
        Subject subject,
        String question
) {}
