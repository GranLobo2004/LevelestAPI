package es.zenith.levelestapi.dto;

import java.sql.Blob;

public record InsigniaDTO(
    Long id,
    String nombre,
    String description,
    Blob image
) {}
