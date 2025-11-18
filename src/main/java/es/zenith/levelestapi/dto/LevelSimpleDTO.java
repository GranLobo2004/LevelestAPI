package es.zenith.levelestapi.dto;

import es.zenith.levelestapi.Enumeration.Subject;

import java.util.List;


public record LevelSimpleDTO(
        Long id,
        Subject subject,
        String question,
        List<String> possibleAnswers
) {}
