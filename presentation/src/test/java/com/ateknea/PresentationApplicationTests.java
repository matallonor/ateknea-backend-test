package com.ateknea;

import com.ateknea.controllers.ApplicationController;
import com.ateknea.controllers.UserController;
import com.ateknea.dto.UserRequest;
import com.ateknea.entities.User;
import com.ateknea.mappers.UserMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration()
public class PresentationApplicationTests {

    private MockMvc mockMvc;

    @Mock
    private ApplicationController applicationController;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserController userController;
    private UserRequest request;
    private User user;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(userController).build();

        request = new UserRequest();
        request.setName("Peter");
        request.setLastName("Parker");
        request.setEmail("pp@mail.com");
        request.setEnabled(true);

        user = new User();
        user.setName(request.getName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setEnabled(request.isEnabled());
    }

    @Test
    public void shouldReachGetAllUsersEndpoint() throws Exception {
        mockMvc.perform(get("/user")
                .characterEncoding("UTF-8")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldReachCreateUserEndpoint() throws Exception {

        when(userMapper.toUser(Mockito.any(UserRequest.class))).thenReturn(user);
        when(applicationController.createUser(Mockito.any(User.class))).thenReturn(user);

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(request);

        mockMvc.perform(post("/user")
                .content(json)
                .characterEncoding("UTF-8")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    public void shouldReachUpdateUserEndpoint() throws Exception {

        when(userMapper.toUser(Mockito.any(UserRequest.class))).thenReturn(user);
        when(applicationController.updateUser(Mockito.any(Long.class), Mockito.any(User.class))).thenReturn(user);

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(request);

        mockMvc.perform(put("/user/1")
                .content(json)
                .characterEncoding("UTF-8")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

}