package es.zenith.levelestapi.mapper;

import es.zenith.levelestapi.domain.Image;
import es.zenith.levelestapi.dto.ImageDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ImageMapper {
    ImageDTO toDTO(Image image);
    Image toEntity(ImageDTO imageDTO);
}
