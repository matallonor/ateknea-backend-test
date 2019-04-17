package com.ateknea.mappers;

import com.ateknea.dto.UserRequest;
import com.ateknea.entities.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public class UserMapper {

    public User toUser(UserRequest request) {
        User user = new User();
        user.setName(request.getName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setEnabled(request.isEnabled());
        return user;
    }

}
