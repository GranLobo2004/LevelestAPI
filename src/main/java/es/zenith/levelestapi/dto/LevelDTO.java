package es.zenith.levelestapi.dto;

import es.zenith.levelestapi.Enumeration.Subject;

public record LevelDTO(
    Long id,
    String subject,
    String question,
    String answer
) {}
