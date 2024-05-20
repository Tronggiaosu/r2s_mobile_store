package com.example.r2s_mobile_store.mapper;

import com.example.r2s_mobile_store.dto.RegisterDTO;
import com.example.r2s_mobile_store.dto.UserDTO;
import com.example.r2s_mobile_store.entity.Role;
import com.example.r2s_mobile_store.entity.User;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-05-20T07:06:33+0700",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.38.0.v20240417-1011, environment: Java 17.0.10 (Eclipse Adoptium)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public RegisterDTO toDTO(User user) {
        if ( user == null ) {
            return null;
        }

        RegisterDTO registerDTO = new RegisterDTO();

        registerDTO.setRole( userRoleId( user ) );
        registerDTO.setAddress( user.getAddress() );
        registerDTO.setDateOfBirth( user.getDateOfBirth() );
        registerDTO.setEmail( user.getEmail() );
        registerDTO.setGender( user.getGender() );
        registerDTO.setPassword( user.getPassword() );
        registerDTO.setPhoneNumber( user.getPhoneNumber() );
        registerDTO.setStatus( user.isStatus() );
        registerDTO.setUsername( user.getUsername() );

        return registerDTO;
    }

    @Override
    public User toEntity(RegisterDTO registerDTO) {
        if ( registerDTO == null ) {
            return null;
        }

        User user = new User();

        user.setRole( registerDTOToRole( registerDTO ) );
        user.setAddress( registerDTO.getAddress() );
        user.setDateOfBirth( registerDTO.getDateOfBirth() );
        user.setEmail( registerDTO.getEmail() );
        user.setGender( registerDTO.getGender() );
        user.setPassword( registerDTO.getPassword() );
        user.setPhoneNumber( registerDTO.getPhoneNumber() );
        user.setStatus( registerDTO.isStatus() );
        user.setUsername( registerDTO.getUsername() );

        return user;
    }

    @Override
    public UserDTO toUserDTO(User user) {
        if ( user == null ) {
            return null;
        }

        UserDTO userDTO = new UserDTO();

        userDTO.setRole( userRoleId( user ) );
        userDTO.setAddress( user.getAddress() );
        userDTO.setDateOfBirth( user.getDateOfBirth() );
        userDTO.setEmail( user.getEmail() );
        userDTO.setGender( user.getGender() );
        userDTO.setPassword( user.getPassword() );
        userDTO.setPhoneNumber( user.getPhoneNumber() );
        userDTO.setStatus( user.isStatus() );
        userDTO.setUsername( user.getUsername() );
        userDTO.setId( user.getId() );

        return userDTO;
    }

    @Override
    public User toEntity(UserDTO userDTO) {
        if ( userDTO == null ) {
            return null;
        }

        User user = new User();

        user.setRole( userDTOToRole( userDTO ) );
        user.setAddress( userDTO.getAddress() );
        user.setDateOfBirth( userDTO.getDateOfBirth() );
        user.setEmail( userDTO.getEmail() );
        user.setGender( userDTO.getGender() );
        user.setId( userDTO.getId() );
        user.setPassword( userDTO.getPassword() );
        user.setPhoneNumber( userDTO.getPhoneNumber() );
        user.setStatus( userDTO.isStatus() );
        user.setUsername( userDTO.getUsername() );

        return user;
    }

    private Long userRoleId(User user) {
        if ( user == null ) {
            return null;
        }
        Role role = user.getRole();
        if ( role == null ) {
            return null;
        }
        long id = role.getId();
        return id;
    }

    protected Role registerDTOToRole(RegisterDTO registerDTO) {
        if ( registerDTO == null ) {
            return null;
        }

        Role role = new Role();

        if ( registerDTO.getRole() != null ) {
            role.setId( registerDTO.getRole() );
        }

        return role;
    }

    protected Role userDTOToRole(UserDTO userDTO) {
        if ( userDTO == null ) {
            return null;
        }

        Role role = new Role();

        if ( userDTO.getRole() != null ) {
            role.setId( userDTO.getRole() );
        }

        return role;
    }
}
