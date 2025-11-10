package es.zenith.levelestapi.dto;

import es.zenith.levelestapi.Enumeration.InsigniaType;

public record InsigniaDTO(
    Long id,
    String nombre,
    String description,
    InsigniaType type,
    ImageDTO image
) {}
