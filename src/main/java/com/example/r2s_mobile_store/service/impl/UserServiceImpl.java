package com.example.r2s_mobile_store.service.impl;

import java.util.Optional;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.r2s_mobile_store.dto.RegisterDTO;
import com.example.r2s_mobile_store.dto.UserDTO;
import com.example.r2s_mobile_store.entity.Role;
import com.example.r2s_mobile_store.entity.User;
import com.example.r2s_mobile_store.exception.NotFoundException;
import com.example.r2s_mobile_store.exception.RoleNotFoundException;
import com.example.r2s_mobile_store.exception.UserNotFoundException;
import com.example.r2s_mobile_store.mapper.UserMapper;
import com.example.r2s_mobile_store.repository.RoleRepository;
import com.example.r2s_mobile_store.repository.UserRepository;
import com.example.r2s_mobile_store.service.UserService;

import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserMapper userMapper;

    public UserServiceImpl(
            UserRepository userRepository,
            RoleRepository roleRepository,
            BCryptPasswordEncoder bCryptPasswordEncoder,
            UserMapper userMapper) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userMapper = userMapper;
    }

    /**
     * Tạo mới người dùng.
     *
     * @param RegisterDTO Đối tượng RegisterDTO chứa thông tin người dùng cần tạo
     *                    mới
     * @return Đối tượng UserDTO đã được tạo mới
     * @throws Exception Nếu xảy ra lỗi trong quá trình tạo mới người dùng
     */
    @Transactional
    @Override
    public UserDTO create(RegisterDTO registerDTO) throws Exception {
        // Kiểm tra tên người dùng đã tồn tại chưa
        Optional<User> existedUser = userRepository.findByUsername(registerDTO.getUsername());
        if (existedUser.isPresent()) {
            throw new Exception("Tên đăng nhập đã đăng ký trước đó!");
        }

        // Kiểm tra email người dùng đã tồn tại chưa
        Optional<User> existedEmail = userRepository.findByEmail(registerDTO.getEmail());
        if (existedEmail.isPresent()) {
            throw new Exception("Email người dùng đã đăng ký trước đó!");
        }

        // Tạo người dùng mới
        registerDTO.setPassword(bCryptPasswordEncoder.encode(registerDTO.getPassword()));

        Role role = roleRepository.findById(registerDTO.getRole())
                .orElseThrow(() -> new NotFoundException(registerDTO.getRole()));

        User user = userMapper.toEntity(registerDTO);
        user.setRole(role);
        user.setStatus(true);
        user = userRepository.save(user);
        return userMapper.toUserDTO(user);
    }

    /**
     * Cập nhật thông tin người dùng hiện tại.
     *
     * @param createUserDTO Đối tượng CreateUserDTO chứa thông tin người dùng cần
     *                      cập nhật
     * @return Đối tượng UserDTO đã được cập nhật
     * @throws Exception Nếu xảy ra lỗi trong quá trình cập nhật người dùng
     */
    @Transactional
    @Override
    public UserDTO updateUserCurrent(RegisterDTO registerDTO) throws UserNotFoundException {
        String username = getUsernameOfCurrentLoginUser();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("Người dùng không tồn tại"));
        user.setPassword(bCryptPasswordEncoder.encode(registerDTO.getPassword()));
        user.setAddress(registerDTO.getAddress());
        user.setEmail(registerDTO.getEmail());
        user.setDateOfBirth(registerDTO.getDateOfBirth());
        user.setGender(registerDTO.getGender());
        user.setPhoneNumber(registerDTO.getPhoneNumber());
        user.setStatus(registerDTO.isStatus());
        Role role = roleRepository.findById(registerDTO.getRole())
                .orElseThrow(() -> new RoleNotFoundException("Vai trò không tồn tại!"));
        user.setRole(role);
        return userMapper.toUserDTO(userRepository.save(user));
    }

    @Transactional(readOnly = true)
    @Override
    public UserDTO getCurrentUser() throws UserNotFoundException {
        String username = getUsernameOfCurrentLoginUser();
        return userMapper.toUserDTO(userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("Người dùng không tồn tại!")));
    }

    @Override
    public String getUsernameOfCurrentLoginUser() throws UserNotFoundException {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        if (username.equals("anonymousUser")) {
            throw new UserNotFoundException("Vui lòng đăng nhập!");
        }
        return username;
    }
}
