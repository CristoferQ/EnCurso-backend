package com.encurso.persistence;

import com.encurso.domain.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("In Memory User Repository Tests")
class InMemoryUserRepositoryTest {

    private InMemoryUserRepository repository;
    private User user;

    @BeforeEach
    void setUp() {
        repository = new InMemoryUserRepository();
        user = new User(1L, "John Doe", "john@email.com", "SecurePass1!", "student");
    }

    @Test
    @DisplayName("Should save user successfully")
    void testSaveUserSuccessfully() {
        repository.save(user);

        Optional<User> result = repository.findById(1L);
        assertTrue(result.isPresent());
        assertEquals("John Doe", result.get().getName());
    }

    @Test
    @DisplayName("Should not save user with null id")
    void testSaveUserWithNullId() {
        user.setId(null);
        repository.save(user);

        Optional<User> result = repository.findById(1L);
        assertFalse(result.isPresent());
    }

    @Test
    @DisplayName("Should not save null user")
    void testSaveNullUser() {
        assertDoesNotThrow(() -> repository.save(null));

        Optional<User> result = repository.findById(1L);
        assertFalse(result.isPresent());
    }

    @Test
    @DisplayName("Should find user by id")
    void testFindUserById() {
        repository.save(user);

        Optional<User> result = repository.findById(1L);
        assertTrue(result.isPresent());
        assertEquals(1L, result.get().getId());
        assertEquals("John Doe", result.get().getName());
    }

    @Test
    @DisplayName("Should return empty optional when user not found")
    void testFindUserNotFound() {
        Optional<User> result = repository.findById(999L);
        assertFalse(result.isPresent());
    }

    @Test
    @DisplayName("Should update user when saving with same id")
    void testUpdateUserWhenSavingWithSameId() {
        repository.save(user);

        User updatedUser = new User(1L, "Jane Doe", "jane@email.com", "SecurePass1!", "instructor");
        repository.save(updatedUser);

        Optional<User> result = repository.findById(1L);
        assertTrue(result.isPresent());
        assertEquals("Jane Doe", result.get().getName());
        assertEquals("jane@email.com", result.get().getEmail());
    }

    @Test
    @DisplayName("Should save multiple users")
    void testSaveMultipleUsers() {
        User user2 = new User(2L, "Jane Doe", "jane@email.com", "SecurePass1!", "instructor");
        User user3 = new User(3L, "Bob Smith", "bob@email.com", "SecurePass1!", "student");

        repository.save(user);
        repository.save(user2);
        repository.save(user3);

        assertTrue(repository.findById(1L).isPresent());
        assertTrue(repository.findById(2L).isPresent());
        assertTrue(repository.findById(3L).isPresent());
    }

    @Test
    @DisplayName("Should preserve user data after save")
    void testPreserveUserDataAfterSave() {
        repository.save(user);

        Optional<User> result = repository.findById(1L);
        assertTrue(result.isPresent());
        assertEquals("john@email.com", result.get().getEmail());
        assertEquals("SecurePass1!", result.get().getPassword());
        assertEquals("student", result.get().getRole());
    }

    @Test
    @DisplayName("Should use ConcurrentHashMap for thread safety")
    void testThreadSafetyWithMultipleSaves() throws InterruptedException {
        Thread thread1 = new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                User u = new User((long) i, "User" + i, "user" + i + "@email.com", "SecurePass1!", "student");
                repository.save(u);
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 6; i <= 10; i++) {
                User u = new User((long) i, "User" + i, "user" + i + "@email.com", "SecurePass1!", "student");
                repository.save(u);
            }
        });

        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();

        assertTrue(repository.findById(1L).isPresent());
        assertTrue(repository.findById(5L).isPresent());
        assertTrue(repository.findById(6L).isPresent());
        assertTrue(repository.findById(10L).isPresent());
    }
}
