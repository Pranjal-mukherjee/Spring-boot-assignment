package com.zemoso.springboot.assignment.controller;

import com.zemoso.springboot.assignment.DTO.UserDTO;
import com.zemoso.springboot.assignment.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllUser() {
        // Prepare test data
        UserDTO user1 = new UserDTO();
        user1.setId(1L);
        user1.setFirstName("User1");

        UserDTO user2 = new UserDTO();
        user2.setId(2L);
        user2.setFirstName("User2");

        List<UserDTO> expectedUsers = Arrays.asList(user1, user2);

        // Mock the userService.getAllUsers() method
        when(userService.getAllUsers()).thenReturn(expectedUsers);

        // Call the controller method
        ResponseEntity<List<UserDTO>> response = userController.getAllUser();

        // Verify the result
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedUsers, response.getBody());

        // Verify that userService.getAllUsers() was called
        verify(userService, times(1)).getAllUsers();
    }

    @Test
    public void testGetUserById() {
        // Prepare test data
        Long userId = 1L;
        UserDTO expectedUser = new UserDTO();
        expectedUser.setId(userId);
        expectedUser.setFirstName("User1");

        // Mock the userService.getUserById() method
        when(userService.getUserById(userId)).thenReturn(expectedUser);

        // Call the controller method
        ResponseEntity<UserDTO> response = userController.getUserById(userId);

        // Verify the result
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedUser, response.getBody());

        // Verify that userService.getUserById() was called
        verify(userService, times(1)).getUserById(userId);
    }

    // Similarly, write tests for createUser, updateUser, and deleteUser methods
    @Test
    public void testCreateUser() {
        // Prepare test data
        UserDTO userDTO = new UserDTO();
        userDTO.setFirstName("JohnDoe");

        UserDTO createdUser = new UserDTO();
        createdUser.setId(1L);
        createdUser.setFirstName("JohnDoe");

        // Mock the userService.createUser() method
        when(userService.createUser(userDTO)).thenReturn(createdUser);

        // Call the controller method
        ResponseEntity<UserDTO> response = userController.createUser(userDTO);

        // Verify the result
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(createdUser, response.getBody());

        // Verify that userService.createUser() was called
        verify(userService, times(1)).createUser(userDTO);
    }

    @Test
    public void testUpdateUser() {
        // Prepare test data
        UserDTO userDTO = new UserDTO();
        userDTO.setId(1L);
        userDTO.setFirstName("UpdatedJohnDoe");

        UserDTO updatedUser = new UserDTO();
        updatedUser.setId(1L);
        updatedUser.setFirstName("UpdatedJohnDoe");

        // Mock the userService.updateUser() method
        when(userService.updateUser(userDTO)).thenReturn(updatedUser);

        // Call the controller method
        ResponseEntity<UserDTO> response = userController.updateUser(userDTO);

        // Verify the result
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedUser, response.getBody());

        // Verify that userService.updateUser() was called
        verify(userService, times(1)).updateUser(userDTO);
    }

    @Test
    public void testDeleteUser() {
        // Prepare test data
        Long userId = 1L;

        // Call the controller method
        ResponseEntity<Void> response = userController.deleteUsere(userId);

        // Verify the result
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());

        // Verify that userService.deleteUser() was called
        verify(userService, times(1)).deleteUser(userId);
    }


}
