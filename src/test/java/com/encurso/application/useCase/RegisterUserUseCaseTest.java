package com.encurso.application.useCase;

import com.encurso.domain.entity.User;
import com.encurso.domain.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@DisplayName("Register User Use Case Tests")
class RegisterUserUseCaseTest {

    private RegisterUserUseCase registerUserUseCase;
    private UserRepository userRepository;
    private User user;

    @BeforeEach
    void setUp() {
        userRepository = mock(UserRepository.class);
        registerUserUseCase = new RegisterUserUseCase(userRepository);
        user = new User(1L, "John Doe", "john@email.com", "SecurePass123!", "student");
    }

    @Test
    @DisplayName("Should register user successfully when user does not exist")
    void testRegisterUserSuccessfully() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        assertDoesNotThrow(() -> registerUserUseCase.execute(user));

        verify(userRepository, times(1)).save(user);
    }

    @Test
    @DisplayName("Should throw IllegalArgumentException when user already exists")
    void testRegisterUserAlreadyExists() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        assertThrows(IllegalArgumentException.class, () -> registerUserUseCase.execute(user));
        verify(userRepository, never()).save(user);
    }

    @Test
    @DisplayName("Should save user exactly once when registering")
    void testSaveIsCalledOnce() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        registerUserUseCase.execute(user);

        verify(userRepository, times(1)).save(user);
    }

    @Test
    @DisplayName("Should verify findById before saving")
    void testFindByIdIsCalledBeforeSave() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        registerUserUseCase.execute(user);

        verify(userRepository).findById(1L);
        verify(userRepository).save(user);
    }

    @Test
    @DisplayName("Should throw exception with correct message when user exists")
    void testExceptionMessageWhenUserExists() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, 
            () -> registerUserUseCase.execute(user));
        
        assertEquals("El ID del usuario ya existe.", exception.getMessage());
    }

    @Test
    @DisplayName("Should register different users with different ids")
    void testRegisterMultipleUsers() {
        User user1 = new User(1L, "User One", "user1@email.com", "SecurePass123!", "student");
        User user2 = new User(2L, "User Two", "user2@email.com", "SecurePass123!", "student");

        when(userRepository.findById(1L)).thenReturn(Optional.empty());
        when(userRepository.findById(2L)).thenReturn(Optional.empty());

        assertDoesNotThrow(() -> registerUserUseCase.execute(user1));
        assertDoesNotThrow(() -> registerUserUseCase.execute(user2));

        verify(userRepository, times(1)).save(user1);
        verify(userRepository, times(1)).save(user2);
    }
}
