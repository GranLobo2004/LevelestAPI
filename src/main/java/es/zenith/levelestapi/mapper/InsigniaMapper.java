package es.zenith.levelestapi.mapper;

import es.zenith.levelestapi.domain.Insignia;
import es.zenith.levelestapi.dto.InsigniaDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface InsigniaMapper {
    InsigniaDTO toDTO(Insignia insignia);
    Insignia toEntity(InsigniaDTO insigniaDTO);
}
