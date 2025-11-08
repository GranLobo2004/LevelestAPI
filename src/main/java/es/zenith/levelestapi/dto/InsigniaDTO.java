package es.zenith.levelestapi.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Blob;

public record InsigniaDTO(
    Long id,
    String nombre,
    String description,
    ImageDTO image
) {}
