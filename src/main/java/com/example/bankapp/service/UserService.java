package com.example.bankapp.service;

import com.example.bankapp.pojo.UserDto;

public interface UserService {

    UserDto createUser(UserDto userDto);

    UserDto getUser(Long id);
}

