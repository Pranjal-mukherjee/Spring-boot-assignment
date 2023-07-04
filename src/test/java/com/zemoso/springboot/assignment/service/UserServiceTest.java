package com.zemoso.springboot.assignment.service;

import com.zemoso.springboot.assignment.DTO.UserDTO;
import com.zemoso.springboot.assignment.entity.Book;
import com.zemoso.springboot.assignment.entity.User;
import com.zemoso.springboot.assignment.repository.BookRepository;
import com.zemoso.springboot.assignment.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllUsers_ReturnsListOfUsers() {
        // Arrange
        List<User> userList = new ArrayList<>();
        userList.add(new User(1L, "John", "Doe", "john.doe@example.com", new Book()));
        userList.add(new User(2L, "Jane", "Smith", "jane.smith@example.com", new Book()));

        when(userRepository.findAll()).thenReturn(userList);

        // Act
        List<UserDTO> result = userService.getAllUsers();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("John", result.get(0).getFirstName());
        assertEquals("Doe", result.get(0).getLastName());
        assertEquals("john.doe@example.com", result.get(0).getEmail());
        // Verify that the repository method was called
        verify(userRepository, times(1)).findAll();
    }

    @Test
    void getUserById_ExistingId_ReturnsUserDTO() {
        // Arrange
        Long id = 1L;
        User user = new User(id, "John", "Doe", "john.doe@example.com", new Book());

        when(userRepository.findById(id)).thenReturn(Optional.of(user));

        // Act
        UserDTO result = userService.getUserById(id);

        // Assert
        assertNotNull(result);
        assertEquals("John", result.getFirstName());
        assertEquals("Doe", result.getLastName());
        assertEquals("john.doe@example.com", result.getEmail());
        // Verify that the repository method was called
        verify(userRepository, times(1)).findById(id);
    }

    @Test
    void getUserById_NonexistentId_ThrowsNoSuchElementException() {
        // Arrange
        Long id = 1L;

        when(userRepository.findById(id)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(NoSuchElementException.class, () -> userService.getUserById(id));
        // Verify that the repository method was called
        verify(userRepository, times(1)).findById(id);
    }





    @Test
    void deleteUser_ExistingId_DeletesUser() {
        // Arrange
        Long userId = 1L;

        // Act
        userService.deleteUser(userId);

        // Verify that the repository method was called
        verify(userRepository, times(1)).deleteById(userId);
    }
}
