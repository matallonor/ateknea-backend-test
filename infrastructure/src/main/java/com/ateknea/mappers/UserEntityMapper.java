package com.ateknea.mappers;

import com.ateknea.entities.User;
import com.ateknea.entities.UserEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserEntityMapper {

    UserEntity toEntity(User user);

    List<User> toUsersList(List<UserEntity> list);

}