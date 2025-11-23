package com.example.bankapp.service.Impl;

import com.example.bankapp.pojo.UserDto;
import com.example.bankapp.service.UserService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final Map<Long, UserDto> users = new HashMap<>();

    @Override
    public UserDto createUser(UserDto userDto) {
        if (users.containsKey(userDto.getId())) {
            throw new IllegalArgumentException("User with ID " + userDto.getId() + " already exists!");
        }
        users.put(userDto.getId(), userDto);
        return userDto;
    }


    @Override
    public UserDto updateUser(Long id, UserDto userDto) {
        if (!users.containsKey(id)) {
            throw new IllegalArgumentException("User not found");
        }
        userDto.setId(id); // keep the same ID
        users.put(id, userDto);
        return userDto;
    }

    @Override
    public void deleteUser(Long id) {
        users.remove(id);
    }
    @Override
    public UserDto getUser(Long id) {
        UserDto user = users.get(id);
        if (user == null) {
            throw new NoSuchElementException("User not found");
        }
        return user;
    }

    @Override
    public List<UserDto> searchUsersByUsername(String username) {
        List<UserDto> results = users.values().stream()
                .filter(user -> user.getName().equalsIgnoreCase(username))
                .collect(Collectors.toList());

        if (results.isEmpty()) {
            throw new NoSuchElementException("User not found");
        }
        return results;
    }


}
