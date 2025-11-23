package com.example.bankapp.service.Impl;

import com.example.bankapp.pojo.UserDto;
import com.example.bankapp.service.UserService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    private final Map<Long, UserDto> users = new HashMap<>();

    @Override
    public UserDto createUser(UserDto userDto) {
        users.put(userDto.getId(), userDto);
        return userDto;
    }

    @Override
    public UserDto getUser(Long id) {
        return users.get(id);
    }
}