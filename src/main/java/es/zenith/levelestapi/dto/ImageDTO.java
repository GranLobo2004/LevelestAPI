package es.zenith.levelestapi.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;

import java.sql.Blob;

public record ImageDTO(
    long id,
    @JsonIgnore
    Blob imageFile,
    String contentType
) {}
