package com.zemoso.springboot.assignment.controller;

import com.zemoso.springboot.assignment.DTO.UserDTO;
import com.zemoso.springboot.assignment.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/users")
public class UserController {


    private UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // add mapping for GET /Books
    @GetMapping("/")
    public ResponseEntity<List<UserDTO>> getAllUser() {
        List<UserDTO> userDTOS = userService.getAllUsers();
        return ResponseEntity.ok(userDTOS);
    }

    // add mapping for GET /Books/{BookId}
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        UserDTO userDTO = userService.getUserById(id);
        return ResponseEntity.ok(userDTO);
    }

    // add mapping for POST /Books - add new Book
    @PostMapping("/")
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
        UserDTO createUserDTO = userService.createUser(userDTO);
        return ResponseEntity.ok(createUserDTO);
    }

    // add mapping for UPDATE /Books/{BookId} - update existing Books
    @PutMapping("/")
    public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO userDTO) {
        UserDTO updatedUser = userService.updateUser(userDTO);
        return ResponseEntity.ok(updatedUser);
    }

    // add mapping for DELETE /Books/{BookId} - delete Books by id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUsere(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
        //In this case, ResponseEntity.noContent() is used to create a response with an HTTP status
        // code of 204 (No Content), which signifies a
        // successful deletion operation. The .build() method is called to finalize and
        // return the ResponseEntity.
    }
}
