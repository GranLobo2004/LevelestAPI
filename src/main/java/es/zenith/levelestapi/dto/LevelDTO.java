package es.zenith.levelestapi.dto;

import java.util.List;

public record LevelDTO(
    Long id,
    String subject,
    String question,
    List<String> possibleAnswers,
    String answer
) {}
