package com.ateknea.application;

import com.ateknea.controllers.ApplicationController;
import com.ateknea.entities.User;
import com.ateknea.exceptions.BadRequestException;
import com.ateknea.services.UserDBService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration()
public class ApplicationTests {

    @Mock
    private UserDBService userDBService;

    @InjectMocks
    private ApplicationController applicationController;

    private User user;
    private User invalidUser;

    @Before
    public void setup() throws BadRequestException {
        MockitoAnnotations.initMocks(this);

        user = new User();
        user.setId(1L);
        user.setName("Peter");
        user.setLastName("Parker");
        user.setEmail("pp@mail.com");
        user.setEnabled(true);

        applicationController.createUser(user);

        invalidUser = new User();
        invalidUser.setId(2L);
        invalidUser.setName("Peter");
        invalidUser.setLastName("Parker");
        invalidUser.setEmail("ppmail.com");
        invalidUser.setEnabled(true);
    }

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test
    public void shouldGetAllUsers() throws Exception {
        List<User> u = new ArrayList<>();
        u.add(user);
        when(userDBService.getAll()).thenReturn(u);

        List<User> users = applicationController.getUsers();
        Assert.assertNotNull(users);
    }

    @Test
    public void shouldCreateUser() throws Exception {
        when(userDBService.create(Mockito.any(User.class))).thenReturn(user);

        User createdUser = applicationController.createUser(user);
        Assert.assertNotNull(createdUser);
        Assert.assertEquals(user.getName(), createdUser.getName());
    }

    @Test
    public void shouldThrowBadRequestException_WhenCreatingInvalidUser() throws BadRequestException {
        exception.expect(BadRequestException.class);
        applicationController.createUser(invalidUser);
    }

    @Test
    public void shouldUpdateUser() throws Exception {
        when(userDBService.update(Mockito.any(Long.class), Mockito.any(User.class))).thenReturn(user);

        user.setName("Tony");
        User updatedUser = applicationController.updateUser(user.getId(), user);
        Assert.assertNotNull(updatedUser);
        Assert.assertEquals(user.getName(), updatedUser.getName());
    }

    @Test
    public void shouldThrowBadRequestException_WhenUpdatingInvalidUser() throws BadRequestException {
        exception.expect(BadRequestException.class);
        applicationController.updateUser(1L, invalidUser);
    }

    @Test
    public void shouldReturnNull_WhenUpdatingInvalidUser() throws BadRequestException {
        User updatedUser = applicationController.updateUser(-1L, user);
        Assert.assertNull(updatedUser);
    }

    @Test
    public void shouldDeleteUser() throws Exception {
        when(userDBService.delete(Mockito.any(Long.class))).thenReturn(user);

        User deletedUser = applicationController.deleteUser(user.getId());
        Assert.assertNotNull(deletedUser);
    }

}
