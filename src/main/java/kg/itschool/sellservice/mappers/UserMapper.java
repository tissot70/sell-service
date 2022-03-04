package kg.itschool.sellservice.mappers;

import kg.itschool.sellservice.models.dtos.user.UserCreateDTO;
import kg.itschool.sellservice.models.dtos.user.UserResponseDTO;
import kg.itschool.sellservice.models.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE= Mappers.getMapper(UserMapper.class);

    UserCreateDTO userToUserCreateDTO(User user);
    User userCreateDTOToUser(UserCreateDTO userCreateDTO);

    UserResponseDTO userToUserResponse(User user);
    User userResponseToUser(UserResponseDTO userResponseDTO);
}
