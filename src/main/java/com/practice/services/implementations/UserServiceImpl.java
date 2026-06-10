package com.practice.services.implementations;

import com.practice.dto.UserDTO.UserCreateDTO;
import com.practice.dto.UserDTO.UserResponseDTO;
import com.practice.mappers.UserMapper;
import com.practice.models.Role;
import com.practice.models.User;
import com.practice.repositories.UserRepository;
import com.practice.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Slf4j
@Service
public class UserServiceImpl implements UserDetailsService, UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserResponseDTO saveUser(UserCreateDTO userCreateDTO) {
        User user = userMapper.toEntity(userCreateDTO);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEnabled(true);
        user.setRole(Role.ROLE_USER);
        User saved = userRepository.save(user);
        log.info("User with username: {} and Role: {} Created Successfully", saved.getUsername(), saved.getRole());
        return userMapper.toResponseDTO(saved);
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
            return userRepository.findByUsername(username)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
    }
}
