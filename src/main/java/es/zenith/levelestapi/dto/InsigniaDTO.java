package es.zenith.levelestapi.dto;

import es.zenith.levelestapi.Enumeration.InsigniaType;

public record InsigniaDTO(
    Long id,
    String name,
    String description,
    InsigniaType type,
    ImageDTO image
) {}
