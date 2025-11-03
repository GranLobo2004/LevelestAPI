package es.zenith.levelestapi.mapper;

import es.zenith.levelestapi.domain.Level;
import es.zenith.levelestapi.dto.LevelDTO;
import es.zenith.levelestapi.dto.LevelSimpleDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LevelMapper {
    LevelDTO toDTO(Level level);
    LevelSimpleDTO toSimpleDTO(Level level);
    Level toEntity(LevelDTO levelDTO);
}
