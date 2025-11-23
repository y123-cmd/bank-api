package com.example.bankapp.controller;

import com.example.bankapp.pojo.UserDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Scanner;
/* this controller is for handling users(add users/ remove users)*/

@RestController
@RequestMapping("/users")
@Tag(name = "User Controller", description = "Operations for creating and retrieving users")
public class UserController {

    @Operation(
            summary = "Create a new user",
            description = "Creates a user and returns the user details"
    )
    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        // dummy logic
        return ResponseEntity.ok(userDto);
    }

    @Operation(
            summary = "Get a user by ID",
            description = "Returns user details for the given user ID"
    )
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable Long id) {
        // dummy user
        UserDto user = new UserDto(id, "John Doe", "john@example.com");
        return ResponseEntity.ok(user);
    }
}