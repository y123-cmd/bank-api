
package com.example.bankapp.service;

import com.example.bankapp.pojo.UserDto;

import java.util.List;

public interface UserService {
    UserDto createUser(UserDto userDto);
    UserDto getUser(Long id);
    UserDto updateUser(Long id, UserDto userDto);
    void deleteUser(Long id);
    List<UserDto> searchUsersByUsername(String username);
}
