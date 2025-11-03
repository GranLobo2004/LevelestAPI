package es.zenith.levelestapi.mapper;

import es.zenith.levelestapi.domain.User;
import es.zenith.levelestapi.dto.UserDTO;
import es.zenith.levelestapi.dto.UserSimpleDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDTO toDTO(User image);
    User toDomain(UserDTO imageDTO);
    UserSimpleDTO toSimpleDTO(User image);
    
}