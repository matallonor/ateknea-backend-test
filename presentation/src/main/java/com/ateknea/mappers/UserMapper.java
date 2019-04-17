package com.ateknea.mappers;

import com.ateknea.dto.UserRequest;
import com.ateknea.entities.User;
import com.ateknea.exceptions.BadRequestException;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public class UserMapper {

//    User toUser(UserRequest request);

    public User toUser(UserRequest request) throws BadRequestException {
        try {
            User user = new User();
            user.setName(request.getName().trim());
            user.setLastName(request.getLastName().trim());
            user.setEmail(request.getEmail());
            user.setEnabled(request.isEnabled());
            return user;
        } catch (Exception e) {
            throw new BadRequestException("Invalid fields");
        }
    }

}
