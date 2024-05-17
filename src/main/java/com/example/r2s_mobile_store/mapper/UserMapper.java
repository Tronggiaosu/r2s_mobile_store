package com.example.r2s_mobile_store.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import com.example.r2s_mobile_store.dto.RegisterDTO;
import com.example.r2s_mobile_store.dto.UserDTO;
import com.example.r2s_mobile_store.entity.User;

@Mapper(componentModel = "spring", uses = { RoleMapper.class })
@Component
public interface UserMapper {
    @Mapping(target = "role", source = "role.id")
    RegisterDTO toDTO(User user);

    @Mapping(target = "role.id", source = "role")
    User toEntity(RegisterDTO registerDTO);

    @Mapping(target = "role", source = "role.id")
    UserDTO toUserDTO(User user);

    @Mapping(target = "role.id", source = "role")
    User toEntity(UserDTO userDTO);
}
