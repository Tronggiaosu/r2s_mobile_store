package com.example.r2s_mobile_store.service;

import com.example.r2s_mobile_store.dto.RegisterDTO;
import com.example.r2s_mobile_store.dto.UserDTO;
import com.example.r2s_mobile_store.exception.UserNotFoundException;

public interface UserService {

    public UserDTO create(RegisterDTO registerDTO) throws Exception;

    UserDTO getCurrentUser() throws UserNotFoundException;

    UserDTO updateUserCurrent(RegisterDTO registerDTO) throws UserNotFoundException;

    String getUsernameOfCurrentLoginUser() throws UserNotFoundException;
}
